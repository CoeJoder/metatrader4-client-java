package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Applied Price. Calculations of technical indicators require price values and/or values of volumes, on which
 * calculations will be performed. There are 7 predefined identifiers from the {@link AppliedPrice} enumeration, used to
 * specify the desired price base for calculations.
 *
 * @see <a href="https://docs.mql4.com/constants/indicatorconstants/prices#enum_applied_price_enum">ENUM_APPLIED_PRICE</a>
 */
public enum AppliedPrice {
    PRICE_CLOSE(0),
    PRICE_OPEN(1),
    PRICE_HIGH(2),
    PRICE_LOW(3),
    PRICE_MEDIAN(4),
    PRICE_TYPICAL(5),
    PRICE_WEIGHTED(6);

    private static final Map<Integer, AppliedPrice> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    AppliedPrice(int id) {
        this.id = id;
    }

    public static Optional<AppliedPrice> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
