package human.coejoder.mt4client;

/**
 * An interval of time on a price chart.
 */
public interface Timeframe {
    /**
     * The length of this timeframe in minutes.
     *
     * @return The number of minutes.
     */
    int getMinutes();
}
