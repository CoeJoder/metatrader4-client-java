package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * The method of swap calculation.
 * <p>
 * This is the MQL4 version; the MQL5 enum has more members and possibly different index values.
 *
 * @see <a href="https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_swap_mode">ENUM_SYMBOL_SWAP_MODE</a>
 * @see <a href="https://docs.mql4.com/convert/stringformat">example usage</a>
 */
@SuppressWarnings("unused")
public enum SymbolSwapMode {
    SYMBOL_SWAP_MODE_POINTS(0),
    SYMBOL_SWAP_MODE_CURRENCY_SYMBOL(1),
    SYMBOL_SWAP_MODE_INTEREST_CURRENT(2),
    SYMBOL_SWAP_MODE_CURRENCY_MARGIN(3);

    private static final Map<Integer, SymbolSwapMode> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    SymbolSwapMode(int id) {
        this.id = id;
    }

    public static Optional<SymbolSwapMode> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
