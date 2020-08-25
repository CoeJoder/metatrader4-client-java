package human.coejoder.mt4client;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class MT4Client implements AutoCloseable {

    private static final int ENABLED = 1;
    private static final int DEFAULT_REQUEST_TIMEOUT_MILLIS = 10000;
    private static final int DEFAULT_RESPONSE_TIMEOUT_MILLIS = 10000;
    private static final boolean VERBOSE = false;

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
        context = new ZContext();
        socket = context.createSocket(SocketType.REQ);
        socket.setSndHWM(ENABLED);
        socket.setRcvHWM(ENABLED);
        socket.setSendTimeOut(requestTimeoutMs);
        socket.setReceiveTimeOut(responseTimeoutMs);

        // connect to server
        socket.connect(address);
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
}
