package human.coejoder.mt4client;

/**
 * The latest prices of a symbol in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/constants/structures/mqltick">mqltick</a>
 */
public class SymbolTick {
    public final int time;
    public final double bid;
    public final double ask;
    public final double last;
    public final int volume;

    /**
     * Constructor.
     *
     * @param time   The time of the last prices update.
     * @param bid    The current bid price.
     * @param ask    The current ask price.
     * @param last   The price of the last deal (Last).
     * @param volume The volume for the current Last price.
     */
    public SymbolTick(int time, double bid, double ask, double last, int volume) {
        this.time = time;
        this.bid = bid;
        this.ask = ask;
        this.last = last;
        this.volume = volume;
    }
}
