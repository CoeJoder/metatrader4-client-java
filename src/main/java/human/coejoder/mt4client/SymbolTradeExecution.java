package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * MetaTrader 4 market symbol deal execution modes.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants#enum_symbol_trade_execution">ENUM_SYMBOL_TRADE_EXECUTION</a>
 */
@SuppressWarnings("unused")
public enum SymbolTradeExecution {
    SYMBOL_TRADE_EXECUTION_REQUEST(0),
    SYMBOL_TRADE_EXECUTION_INSTANT(1),
    SYMBOL_TRADE_EXECUTION_MARKET(2),
    SYMBOL_TRADE_EXECUTION_EXCHANGE(3);     // MQL5 only

    private static final Map<Integer, SymbolTradeExecution> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    SymbolTradeExecution(int id) {
        this.id = id;
    }

    public static Optional<SymbolTradeExecution> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
