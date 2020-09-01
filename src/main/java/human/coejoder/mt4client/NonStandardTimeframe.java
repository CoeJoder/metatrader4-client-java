package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * The non-standard timeframes available for chart data in MetaTrader 4.  These periods can be used for working with
 * offline charts.
 *
 * @see <a href="https://docs.mql4.com/constants/chartconstants/enum_timeframes">enum_timeframes</a>
 */
public enum NonStandardTimeframe implements Timeframe {
    PERIOD_M2(2),
    PERIOD_M3(3),
    PERIOD_M4(4),
    PERIOD_M6(6),
    PERIOD_M10(10),
    PERIOD_M12(12),
    PERIOD_M20(20),
    PERIOD_H2(120),
    PERIOD_H3(180),
    PERIOD_H6(360),
    PERIOD_H8(480),
    PERIOD_H12(720);

    private static final Map<String, NonStandardTimeframe> NAME_TO_ENUM = Stream.of(values()).collect(
            toMap(Enum::toString, e -> e)
    );

    public final int minutes;

    NonStandardTimeframe(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    /**
     * Try to get a {@link NonStandardTimeframe} with the given name.
     *
     * @param name The MT4 name of the timeframe.
     * @return The timeframe object.
     */
    static Optional<Timeframe> fromName(String name) {
        return Optional.ofNullable(NAME_TO_ENUM.get(name));
    }
}
