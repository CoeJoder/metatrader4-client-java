package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class MT4Client implements AutoCloseable {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final int ENABLED = 1;
    private static final int DEFAULT_REQUEST_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_RESPONSE_TIMEOUT_MILLIS = 10000;
    private static final boolean VERBOSE = false;
    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_CODE_DESCRIPTION = "error_code_description";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String WARNING = "warning";
    private static final String RESPONSE = "response";

    private final ZContext context;
    private final ZMQ.Socket socket;
    private boolean verbose = false;

    /**
     * Constructor.  Initialize the REQ socket and connect to the MT4 server.
     *
     * @param address           The address of the server's listening socket.
     * @param requestTimeoutMs  The number of milliseconds to wait for a request to be sent.
     * @param responseTimeoutMs The number of milliseconds to wait for a response to be received.
     */
    public MT4Client(String address, int requestTimeoutMs, int responseTimeoutMs) {
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
     * <p>
     * See {@link #MT4Client(String, int, int)}
     *
     * @param address The address of the server's listening socket.
     */
    public MT4Client(String address) {
        this(address, DEFAULT_REQUEST_TIMEOUT_MILLIS, DEFAULT_RESPONSE_TIMEOUT_MILLIS);
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void shutdown() {
        context.destroy();
    }

    @Override
    public void close() {
        shutdown();
    }

    /**
     * Send a request object to the server and wait for a response.
     *
     * @param request   The request to send.  Must have an `action` property.
     * @return          The server response.
     */
    protected JsonNode getResponse(ObjectNode request) throws JsonProcessingException {
        String strRequest = request.toString();
        socket.send(strRequest);
        printTrace("Request: " + strRequest);
        String strResponse = socket.recvStr();
        printTrace(strResponse == null ? "Response is empty." : "Response: " + strResponse);
        JsonNode response = jsonMapper.readTree(strResponse);

        // throw exception for any errors
        JsonNode errorCode = response.get(ERROR_CODE);
        JsonNode errorCodeDescription = response.get(ERROR_CODE_DESCRIPTION);
        JsonNode errorMessage = response.get(ERROR_MESSAGE);
        if (errorCode != null || errorCodeDescription != null || errorMessage != null) {
            //TODO create MT4Exception class
            throw new RuntimeException("Server-side error!");
        }

        // print any warnings to stdout
        if (response.has(WARNING)) {
            System.out.println(response.get(WARNING).asText());
        }

        // unwrap the response
        return response.get(RESPONSE);
    }

    private void printTrace(String message) {
        if (verbose) {
            System.err.println("[MT4-ZMQ] " + message);
        }
    }
}
