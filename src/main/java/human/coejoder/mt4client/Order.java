package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an order in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/trading">Trading</a>>
 */
public class Order {

    private static final String MAGIC_NUMBER = "magic_number";
    private static final String ORDER_TYPE = "order_type";
    private static final String OPEN_PRICE = "open_price";
    private static final String CLOSE_PRICE = "close_price";
    private static final String OPEN_TIME = "open_time";
    private static final String CLOSE_TIME = "close_time";

    private final int ticket;
    private final int magicNumber;
    private final String symbol;
    private final OrderType orderType;
    private final float lots;
    private final float openPrice;
    private final float closePrice;
    private final String openTime;
    private final String closeTime;
    private final String expiration;
    private final float sl;
    private final float tp;
    private final float profit;
    private final float commission;
    private final float swap;
    private final String comment;

    /**
     * Package-private constructor.
     *
     * @param ticket      The order ticket number.
     * @param magicNumber The identifying (magic) number.
     * @param symbol      The symbol name.
     * @param orderType   The order type.
     * @param lots        Amount of lots (trade volume).
     * @param openPrice   The open price.
     * @param closePrice  The close price.
     * @param openTime    The open date/time.
     * @param closeTime   The close date/time.
     * @param expiration  The expiration date/time.
     * @param sl          The stop-loss.
     * @param tp          The take-profit.
     * @param profit      The net profit (without swaps or commissions). For open orders, it is the current unrealized
     *                    profit. For closed orders, it is the fixed profit.
     * @param commission  The calculated commission.
     * @param swap        The swap value.
     * @param comment     The comment.
     */
    @JsonCreator
    public Order(int ticket,
                 @JsonProperty(MAGIC_NUMBER) int magicNumber,
                 String symbol,
                 @JsonProperty(ORDER_TYPE) int orderType,
                 float lots,
                 @JsonProperty(OPEN_PRICE) float openPrice,
                 @JsonProperty(CLOSE_PRICE) float closePrice,
                 @JsonProperty(OPEN_TIME) String openTime,
                 @JsonProperty(CLOSE_TIME) String closeTime,
                 String expiration,
                 float sl,
                 float tp,
                 float profit,
                 float commission,
                 float swap,
                 String comment) {
        this.ticket = ticket;
        this.magicNumber = magicNumber;
        this.symbol = symbol;
        this.orderType = OrderType.fromId(orderType).orElseThrow();
        this.lots = lots;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.expiration = expiration;
        this.sl = sl;
        this.tp = tp;
        this.profit = profit;
        this.commission = commission;
        this.swap = swap;
        this.comment = comment;
    }

    public int getTicket() {
        return ticket;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public float getLots() {
        return lots;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public String getExpiration() {
        return expiration;
    }

    public float getSl() {
        return sl;
    }

    public float getTp() {
        return tp;
    }

    public float getProfit() {
        return profit;
    }

    public float getCommission() {
        return commission;
    }

    public float getSwap() {
        return swap;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ticket=" + ticket +
                ", magicNumber=" + magicNumber +
                ", symbol='" + symbol + '\'' +
                ", orderType=" + orderType +
                ", lots=" + lots +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", expiration='" + expiration + '\'' +
                ", sl=" + sl +
                ", tp=" + tp +
                ", profit=" + profit +
                ", commission=" + commission +
                ", swap=" + swap +
                ", comment='" + comment + '\'' +
                '}';
    }
}
