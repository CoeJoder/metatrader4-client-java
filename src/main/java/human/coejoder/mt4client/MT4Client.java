package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_CODE_DESCRIPTION = "error_code_description";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String WARNING = "warning";
    private static final String RESPONSE = "response";
    private static final String NAMES = "names";
    private static final TypeReference<List<String>> LIST_OF_STRINGS = new TypeReference<>() {};
    private static final TypeReference<HashMap<String, Symbol>> MAP_OF_SYMBOLS = new TypeReference<>() {};

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
        return getResponse(Request.GET_SYMBOL_INFO.build()
                .set(NAMES, namesArray), MAP_OF_SYMBOLS);
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
            throw new MT4Exception(errorCode, errorCodeDescription, errorMessage);
        }

        // log any warnings
        if (response.has(WARNING)) {
            LOG.warn(response.get(WARNING).asText());
        }

        // unwrap the response
        return response.get(RESPONSE);
    }
}
