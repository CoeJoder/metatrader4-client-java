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

    /**
     * Check if this timeframe is standard.
     *
     * @return Whether or not timeframe is standard.
     */
    default boolean isStandard() {
        return (this instanceof StandardTimeframe);
    }
}
