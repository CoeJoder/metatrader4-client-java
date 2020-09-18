package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The latest prices of a symbol in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/constants/structures/mqltick">mqltick</a>
 */
public class SymbolTick {

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
     * @param time   The time of the last prices update.
     * @param bid    The current bid price.
     * @param ask    The current ask price.
     * @param last   The price of the last deal (Last).
     * @param volume The volume for the current last price.
     */
    @JsonCreator
    SymbolTick(int time, double bid, double ask, double last, int volume) {
        this.time = time;
        this.bid = bid;
        this.ask = ask;
        this.last = last;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "SymbolTick{" +
                "time=" + time +
                ", bid=" + bid +
                ", ask=" + ask +
                ", last=" + last +
                ", volume=" + volume +
                '}';
    }
}
