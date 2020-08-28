package human.coejoder.mt4client.api;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Types of MT4 server requests.
 */
public enum Request {
    GET_ACCOUNT_INFO,
    GET_ACCOUNT_INFO_INTEGER,
    GET_ACCOUNT_INFO_DOUBLE,
    GET_SYMBOL_INFO,
    GET_SYMBOL_MARKET_INFO,
    GET_SYMBOL_TICK,
    GET_ORDER,
    GET_ORDERS,
    GET_HISTORICAL_ORDERS,
    GET_SYMBOLS,
    GET_OHLCV,
    GET_SIGNALS,
    GET_SIGNAL_INFO,
    DO_ORDER_SEND,
    DO_ORDER_CLOSE,
    DO_ORDER_DELETE,
    DO_ORDER_MODIFY,
    RUN_INDICATOR;

    private static final String ACTION = "action";

    public ObjectNode build() {
        return JsonNodeFactory.instance.objectNode()
                .put(ACTION, Request.this.toString());
    }
}
