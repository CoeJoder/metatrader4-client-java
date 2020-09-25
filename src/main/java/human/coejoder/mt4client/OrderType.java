package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * The order types available in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/trading/ordertype">OrderType</a>
 * @see <a href="https://www.mql5.com/en/forum/122847">MQL forum discussion</a>
 */
public enum OrderType {
    OP_BUY(0),
    OP_SELL(1),
    OP_BUYLIMIT(2),
    OP_BUYSTOP(3),
    OP_SELLLIMIT(4),
    OP_SELLSTOP(5),
    // the following types are not for making orders; they represent special broker actions shown in Account History
    OP_BALANCE(6),
    OP_CREDIT(7),
    OP_REBATE(8);

    private static final String MARKET_BUY = "MARKET-BUY";
    private static final String MARKET_SELL = "MARKET-SELL";
    private static final String LIMIT_BUY = "LIMIT-BUY";
    private static final String STOP_BUY = "STOP-BUY";
    private static final String LIMIT_SELL = "LIMIT-SELL";
    private static final String STOP_SELL = "STOP-SELL";
    private static final String BALANCE = "BALANCE";
    private static final String CREDIT = "CREDIT";
    private static final String REBATE = "REBATE";
    private static final String UNKNOWN = "UNKNOWN";
    private static final Map<Integer, OrderType> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    OrderType(int id) {
        this.id = id;
    }

    public static Optional<OrderType> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }

    /**
     * @return Whether the order type is a buy offer.
     */
    public boolean isBuy() {
        return this == OP_BUY || this == OP_BUYLIMIT || this == OP_BUYSTOP;
    }

    /**
     * @return Whether the order type is a sell offer.
     */
    public boolean isSell() {
        return this == OP_SELL || this == OP_SELLLIMIT || this == OP_SELLSTOP;
    }

    /**
     * @return Whether the order type is market.
     */
    public boolean isMarket() {
        return this == OP_BUY || this == OP_SELL;
    }

    /**
     * @return Whether the order type is pending.
     */
    public boolean isPending() {
        return this == OP_BUYLIMIT || this == OP_BUYSTOP || this == OP_SELLLIMIT || this == OP_SELLSTOP;
    }

    @Override
    public String toString() {
        switch (this) {
            case OP_BUY:
                return MARKET_BUY;
            case OP_SELL:
                return MARKET_SELL;
            case OP_BUYLIMIT:
                return LIMIT_BUY;
            case OP_BUYSTOP:
                return STOP_BUY;
            case OP_SELLLIMIT:
                return LIMIT_SELL;
            case OP_SELLSTOP:
                return STOP_SELL;
            case OP_BALANCE:
                return BALANCE;
            case OP_CREDIT:
                return CREDIT;
            case OP_REBATE:
                return REBATE;
            default:
                return UNKNOWN;
        }
    }
}
