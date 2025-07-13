package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MT4Client implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(MT4Client.class);
    private static final int ENABLED = 1;
    private static final int DEFAULT_REQUEST_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_RESPONSE_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_INDICATOR_TIMEOUT = 5000;
    private static final boolean DEFAULT_CLOSE_IF_OPENED = true;
    private static final String ERROR_CODE = MT4Exception.ERROR_CODE;
    private static final String ERROR_CODE_DESCRIPTION = MT4Exception.ERROR_CODE_DESCRIPTION;
    private static final String ERROR_MESSAGE = MT4Exception.ERROR_MESSAGE;
    private static final String WARNING = "warning";
    private static final String RESPONSE = "response";
    private static final String NAMES = "names";
    private static final String INDICATOR = "indicator";
    private static final String ARGV = "argv";
    private static final String TIMEOUT = "timeout";
    private static final String TICKET = "ticket";
    private static final String CLOSE_IF_OPENED = "close_if_opened";
    private static final String SYMBOL = "symbol";
    private static final String TIMEFRAME = "timeframe";
    private static final String LIMIT = "limit";
    private static final TypeReference<List<String>> LIST_OF_STRINGS = new TypeReference<>() {};
    private static final TypeReference<HashMap<String, Symbol>> MAP_OF_SYMBOLS = new TypeReference<>() {};
    private static final TypeReference<HashMap<String, Signal>> MAP_OF_SIGNALS = new TypeReference<>() {};
    private static final TypeReference<List<Order>> LIST_OF_ORDERS = new TypeReference<>() {};
    private static final TypeReference<List<OHLCV>> LIST_OF_OHLCV = new TypeReference<>() {};

    private final ZContext context;
    private final ZMQ.Socket socket;
    private final ObjectMapper objectMapper;

    /**
     * Constructor.  Initialize the REQ socket and connect to the MT4 server.
     *
     * @param address           The address of the server's listening socket.
     * @param requestTimeoutMs  The number of milliseconds to wait for a request to be sent.
     * @param responseTimeoutMs The number of milliseconds to wait for a response to be received.
     */
    public MT4Client(String address, int requestTimeoutMs, int responseTimeoutMs) {
        // create JSON object mapper
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());

        InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(MT4Client.class, this);
        objectMapper.setInjectableValues(injectableValues);

        // create and configure REQ socket
        this.context = new ZContext();
        this.socket = context.createSocket(SocketType.REQ);
        this.socket.setSndHWM(ENABLED);
        this.socket.setRcvHWM(ENABLED);
        this.socket.setSendTimeOut(requestTimeoutMs);
        this.socket.setReceiveTimeOut(responseTimeoutMs);

        // connect to server
        this.socket.connect(address);
    }

    /**
     * Constructor.  Uses a {@link #DEFAULT_REQUEST_TIMEOUT_MILLIS default request timeout} and a {@link
     * #DEFAULT_RESPONSE_TIMEOUT_MILLIS default response timeout}.
     *
     * @see #MT4Client(String, int, int)
     */
    public MT4Client(String address) {
        this(address, DEFAULT_REQUEST_TIMEOUT_MILLIS, DEFAULT_RESPONSE_TIMEOUT_MILLIS);
    }

    public void shutdown() {
        context.destroy();
    }

    @Override
    public void close() {
        shutdown();
    }

    /**
     * Get a query interface for the account details.
     *
     * @return The {@link Account} object.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public Account getAccount() throws JsonProcessingException, MT4Exception {
        return getResponse(Request.GET_ACCOUNT_INFO.build(), Account.class);
    }

    /**
     * Get the names of market symbols supported by the broker.
     *
     * @return A list of symbol names.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public List<String> getSymbolNames() throws JsonProcessingException, MT4Exception {
        return getResponse(Request.GET_SYMBOLS.build(), LIST_OF_STRINGS);
    }

    /**
     * Get query interfaces for market symbols.
     *
     * @param names The names of the symbols.
     * @return A name-to-{@link Symbol} map.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public Map<String, Symbol> getSymbols(String... names) throws JsonProcessingException, MT4Exception {
        if (names.length == 0) {
            return Collections.emptyMap();
        }
        ArrayNode namesArray = JsonNodeFactory.instance.arrayNode(names.length);
        for (String name : names) {
            namesArray.add(name);
        }
        ObjectNode request = Request.GET_SYMBOL_INFO.build()
                .set(NAMES, namesArray);
        return getResponse(request, MAP_OF_SYMBOLS);
    }

    /**
     * Get a query interface for a market symbol.
     *
     * @param name The name of the symbol.
     * @return The {@link Symbol}.
     */
    public Symbol getSymbol(String name) throws JsonProcessingException, MT4Exception {
        return getSymbols(name).get(name);
    }

    /**
     * Get the names of all trading signals.
     *
     * @return A list of names of the available signals.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public List<String> getSignalNames() throws JsonProcessingException, MT4Exception {
        return getResponse(Request.GET_SIGNALS.build(), LIST_OF_STRINGS);
    }

    /**
     * Get the list of last limit OHLCV data.
     *
     * @param symbol The market symbol.
     * @param timeframe The width of the bars, in minutes. Use a standard timeframe for a better chance of the broker's server responding successfully.
     * @param limit The maximum number of bars to return.
     * @param timeout The timeout in milliseconds to wait for the broker's server to return the data.
     * @return A list of OHLCV data objects.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public List<OHLCV> getOHLCV(String symbol, Timeframe timeframe, long limit, long timeout) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.GET_OHLCV.build()
                .<ObjectNode>set(SYMBOL, TextNode.valueOf(symbol))
                .<ObjectNode>set(TIMEFRAME, LongNode.valueOf(timeframe.getMinutes()))
                .<ObjectNode>set(LIMIT, LongNode.valueOf(limit))
                .set(TIMEOUT, LongNode.valueOf(timeout));

        return getResponse(request, LIST_OF_OHLCV);
    }

    /**
     * Get data for multiple trading signals.
     *
     * @param names The names of the signals.
     * @return A name-to-{@link Signal} map.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public Map<String, Signal> getSignals(String... names) throws JsonProcessingException, MT4Exception {
        if (names.length == 0) {
            return Collections.emptyMap();
        }
        ArrayNode namesArray = JsonNodeFactory.instance.arrayNode(names.length);
        for (String name : names) {
            namesArray.add(name);
        }
        ObjectNode request = Request.GET_SIGNAL_INFO.build()
                .set(NAMES, namesArray);
        return getResponse(request, MAP_OF_SIGNALS);
    }

    /**
     * Get data for a trading signal.
     *
     * @param name The name of the signal.
     * @return The signal object.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public Signal getSignal(String name) throws JsonProcessingException, MT4Exception {
        return getSignals(name).get(name);
    }

    /**
     * Run a built-in indicator function, waiting at most {@value DEFAULT_INDICATOR_TIMEOUT} milliseconds for symbol's
     * chart data to load.
     *
     * @param func The {@link Indicator} to run.
     * @return The numeric result.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/indicators">https://docs.mql4.com/indicators</a>
     */
    public double runIndicator(Indicator func) throws JsonProcessingException, MT4Exception {
        return runIndicator(func, DEFAULT_INDICATOR_TIMEOUT);
    }

    /**
     * Run a built-in indicator function.
     *
     * @param func    The {@link Indicator} to run.
     * @param timeout The maximum milliseconds to wait for the symbol's chart data to load.
     * @return The numeric result.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/indicators">https://docs.mql4.com/indicators</a>
     */
    public double runIndicator(Indicator func, int timeout) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.RUN_INDICATOR.build()
                .<ObjectNode>set(INDICATOR, TextNode.valueOf(func.getName()))
                .<ObjectNode>set(ARGV, func.getArguments())
                .set(TIMEOUT, IntNode.valueOf(timeout));
        return getResponse(request, double.class);
    }

    /**
     * Get the pending and open orders from the Trades tab.
     *
     * @return A list of open or pending {@link Order Orders}.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public List<Order> getOrders() throws JsonProcessingException, MT4Exception {
        return getResponse(Request.GET_ORDERS.build(), LIST_OF_ORDERS);
    }

    /**
     * Get the deleted and closed orders from the Account History tab.
     *
     * @return A list of closed {@link Order Orders}.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public List<Order> getOrdersHistorical() throws JsonProcessingException, MT4Exception {
        return getResponse(Request.GET_HISTORICAL_ORDERS.build(), LIST_OF_ORDERS);
    }

    /**
     * Get an order by ticket number.  May be pending, open, or closed.
     *
     * @param ticket The ticket number.
     * @return The {@link Order} object.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public Order getOrder(int ticket) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.GET_ORDER.build()
                .set(TICKET, IntNode.valueOf(ticket));
        return getResponse(request, Order.class);
    }

    /**
     * Create a new order.
     *
     * @param newOrder The {@link NewOrder new order request}.
     * @return The new {@link Order}.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/ordersend">https://docs.mql4.com/trading/ordersend</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public Order orderSend(NewOrder newOrder) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.DO_ORDER_SEND.build()
                .setAll(objectMapper.<ObjectNode>valueToTree(newOrder));
        return getResponse(request, Order.class);
    }

    /**
     * Modify a market or pending order.
     *
     * @param modifyOrder The {@link ModifyOrder modify-order request}.
     * @return The modified {@link Order}.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://book.mql4.com/trading/ordermodify">https://book.mql4.com/trading/ordermodify</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public Order orderModify(ModifyOrder modifyOrder) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.DO_ORDER_MODIFY.build()
                .setAll(objectMapper.<ObjectNode>valueToTree(modifyOrder));
        return getResponse(request, Order.class);
    }

    /**
     * Close an open order.
     *
     * @param ticket The ticket number.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderClose(int ticket) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.DO_ORDER_CLOSE.build()
                .set(TICKET, IntNode.valueOf(ticket));
        getResponse(request);
    }

    /**
     * Close an open order.
     *
     * @param order The order to close.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderClose(Order order) throws JsonProcessingException, MT4Exception {
        orderClose(order.getTicket());
    }

    /**
     * Delete a pending order.
     *
     * @param ticket        The ticket number.
     * @param closeIfOpened If true and the order is open, it is closed at market price.  If false and the order is
     *                      open, an `ERR_INVALID_TICKET` error is raised.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderDelete(int ticket, boolean closeIfOpened) throws JsonProcessingException, MT4Exception {
        ObjectNode request = Request.DO_ORDER_DELETE.build()
                .<ObjectNode>set(TICKET, IntNode.valueOf(ticket))
                .set(CLOSE_IF_OPENED, BooleanNode.valueOf(closeIfOpened));
        getResponse(request);
    }

    /**
     * Delete a pending order.
     *
     * @param order         The order to delete.
     * @param closeIfOpened If true and the order is open, it is closed at market price.  If false and the order is
     *                      open, an `ERR_INVALID_TICKET` error is raised.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderDelete(Order order, boolean closeIfOpened) throws JsonProcessingException, MT4Exception {
        orderDelete(order.getTicket(), closeIfOpened);
    }

    /**
     * Delete a pending order.  If order is open, it is closed at market price.
     *
     * @param ticket The ticket number.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderDelete(int ticket) throws JsonProcessingException, MT4Exception {
        orderDelete(ticket, DEFAULT_CLOSE_IF_OPENED);
    }

    /**
     * Delete a pending order.  If order is open, it is closed at market price.
     *
     * @param order The order to delete.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     * @see <a href="https://docs.mql4.com/trading/orderdelete">https://docs.mql4.com/trading/orderdelete</a>
     * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
     */
    public void orderDelete(Order order) throws JsonProcessingException, MT4Exception {
        orderDelete(order.getTicket());
    }

    /**
     * Send a request object to the server and wait for a response.
     *
     * @param request      The request to send.  Must have an `action` property.
     * @param responseType The response type.
     * @param <T>          The response type.
     * @return The server response.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    <T> T getResponse(ObjectNode request, Class<T> responseType) throws JsonProcessingException, MT4Exception {
        return objectMapper.convertValue(getResponse(request), responseType);
    }

    /**
     * Send a request object to the server and wait for a response.
     *
     * @param request      The request to send.  Must have an `action` property.
     * @param responseType The response type.
     * @param <T>          The response type.
     * @return The server response.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    <T> T getResponse(ObjectNode request, TypeReference<T> responseType) throws JsonProcessingException, MT4Exception {
        return objectMapper.convertValue(getResponse(request), responseType);
    }

    private JsonNode getResponse(ObjectNode request) throws JsonProcessingException, MT4Exception {
        String strRequest = request.toString();
        socket.send(strRequest);
        LOG.trace("Request: " + strRequest);
        String strResponse = socket.recvStr();
        LOG.trace(strResponse == null ? "Response is empty." : "Response: " + strResponse);
        JsonNode response = objectMapper.readTree(strResponse);

        // throw exception for any errors
        JsonNode errorCode = response.get(ERROR_CODE);
        JsonNode errorCodeDescription = response.get(ERROR_CODE_DESCRIPTION);
        JsonNode errorMessage = response.get(ERROR_MESSAGE);
        if (errorCode != null || errorCodeDescription != null || errorMessage != null) {
            throw objectMapper.convertValue(response, MT4Exception.class);
        }

        // log any warnings
        if (response.has(WARNING)) {
            LOG.warn(response.get(WARNING).asText());
        }

        // unwrap the response
        return response.get(RESPONSE);
    }
}
