package human.coejoder.mt4client;

/**
 * An OHLCV bar.
 *
 * @see <a href="https://docs.mql4.com/series/copyrates">copyrates</a>
 * @see <a href="https://docs.mql4.com/constants/structures/mqlrates">mqlrates</a>
 */
public class OHLCV {
    public final int time;
    public final int open;
    public final int high;
    public final int low;
    public final int close;
    public final int tick_volume;

    /**
     * Constructor.
     *
     * @param time        The period start time.
     * @param open        The open price.
     * @param high        The highest price of the period.
     * @param low         The lowest price of the period.
     * @param close       The close price.
     * @param tick_volume The tick volume.
     */
    public OHLCV(int time, int open, int high, int low, int close, int tick_volume) {
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.tick_volume = tick_volume;
    }
}
