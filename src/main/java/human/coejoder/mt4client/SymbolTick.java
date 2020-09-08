package human.coejoder.mt4client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * The latest prices of a symbol in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/constants/structures/mqltick">mqltick</a>
 */
public class SymbolTick {

    private static final String TIME = "time";
    private static final String BID = "bid";
    private static final String ASK = "ask";
    private static final String LAST = "last";
    private static final String VOLUME = "volume";

    /**
     * The time of the last prices update.
     */
    public final int time;

    /**
     * The current bid price.
     */
    public final double bid;

    /**
     * The current ask price.
     */
    public final double ask;

    /**
     * The price of the last deal (Last).
     */
    public final double last;

    /**
     * The volume for the current last price.
     */
    public final int volume;

    /**
     * Package-private constructor.
     *
     * @param response The symbol tick data as returned by the server.
     */
    SymbolTick(JsonNode response) {
        this.time = response.get(TIME).asInt();
        this.bid = response.get(BID).asDouble();
        this.ask = response.get(ASK).asDouble();
        this.last = response.get(LAST).asDouble();
        this.volume = response.get(VOLUME).asInt();
    }
}
