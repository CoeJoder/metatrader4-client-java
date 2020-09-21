package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * MetaTrader 4 market symbol trading mode.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants#enum_symbol_trade_mode">ENUM_SYMBOL_TRADE_MODE</a>
 */
@SuppressWarnings("unused")
public enum SymbolTradeMode {
    SYMBOL_TRADE_MODE_DISABLED(0),
    SYMBOL_TRADE_MODE_LONGONLY(3),          // MQL5 only
    SYMBOL_TRADE_MODE_SHORTONLY(4),         // MQL5 only
    SYMBOL_TRADE_MODE_CLOSEONLY(1),
    SYMBOL_TRADE_MODE_FULL(2);

    private static final Map<Integer, SymbolTradeMode> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    SymbolTradeMode(int id) {
        this.id = id;
    }

    public static Optional<SymbolTradeMode> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
