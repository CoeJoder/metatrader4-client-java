package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * The contract price calculation mode.
 *
 * This is the MQL4 version; the MQL5 enum has more members and possibly different index values.
 *
 * @see <a href="https://www.mql5.com/en/docs/constants/environment_state/marketinfoconstants#enum_symbol_calc_mode">ENUM_SYMBOL_CALC_MODE (mql5)</a>
 * @see <a href="https://docs.mql4.com/convert/stringformat">Example usage (mql4)</a>
 */
@SuppressWarnings("unused")
public enum SymbolCalcMode {
    SYMBOL_CALC_MODE_FOREX(0),
    SYMBOL_CALC_MODE_CFD(1),
    SYMBOL_CALC_MODE_FUTURES(2),
    SYMBOL_CALC_MODE_CFDINDEX(3);

    private static final Map<Integer, SymbolCalcMode> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    SymbolCalcMode(int id) {
        this.id = id;
    }

    public static Optional<SymbolCalcMode> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
