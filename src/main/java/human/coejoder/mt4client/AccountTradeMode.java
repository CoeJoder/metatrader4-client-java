package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * MetaTrader 4 account type.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_trade_mode">ENUM_ACCOUNT_TRADE_MODE</a>
 */
public enum AccountTradeMode {
    ACCOUNT_TRADE_MODE_DEMO(0),
    ACCOUNT_TRADE_MODE_CONTEST(1),
    ACCOUNT_TRADE_MODE_REAL(2);

    private static final Map<Integer, AccountTradeMode> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    AccountTradeMode(int id) {
        this.id = id;
    }

    public static Optional<AccountTradeMode> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
