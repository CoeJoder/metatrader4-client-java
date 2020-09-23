package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Days of the week.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants#enum_day_of_week">ENUM_DAY_OF_WEEK</a>
 */
public enum DayOfWeek {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private static final Map<Integer, DayOfWeek> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    DayOfWeek(int id) {
        this.id = id;
    }

    public static Optional<DayOfWeek> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
