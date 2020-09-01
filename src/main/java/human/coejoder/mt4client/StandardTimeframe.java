package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * The standard timeframes available for chart data in MetaTrader 4.  Online charts of financial instruments can be
 * plotted only on these time intervals.
 *
 * @see <a href="https://docs.mql4.com/constants/chartconstants/enum_timeframes">enum_timeframes</a>
 */
public enum StandardTimeframe implements Timeframe {
    PERIOD_CURRENT(0),
    PERIOD_M1(1),
    PERIOD_M5(5),
    PERIOD_M15(15),
    PERIOD_M30(30),
    PERIOD_H1(60),
    PERIOD_H4(240),
    PERIOD_D1(1440),
    PERIOD_W1(10080),
    PERIOD_MN1(43200);

    private static final Map<String, StandardTimeframe> NAME_TO_ENUM = Stream.of(values()).collect(
            toMap(Enum::toString, e -> e)
    );

    public final int minutes;

    StandardTimeframe(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    /**
     * Try to get a {@link StandardTimeframe} with the given name.
     *
     * @param name The MT4 name of the timeframe.
     * @return The timeframe object.
     */
    static Optional<Timeframe> fromName(String name) {
        return Optional.ofNullable(NAME_TO_ENUM.get(name));
    }
}
