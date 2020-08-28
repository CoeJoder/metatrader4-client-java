package human.coejoder.mt4client;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * MetaTrader 4 account's stop out mode.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_stopout_mode">ENUM_ACCOUNT_STOPOUT_MODE</a>
 */
public enum AccountStopoutMode {
    ACCOUNT_STOPOUT_MODE_PERCENT(0),
    ACCOUNT_STOPOUT_MODE_MONEY(1);

    private static final Map<Integer, AccountStopoutMode> ID_TO_ENUM = Stream.of(values()).collect(
            toMap(e -> e.id, e -> e)
    );

    public final int id;

    AccountStopoutMode(int id) {
        this.id = id;
    }

    public static Optional<AccountStopoutMode> fromId(int id) {
        return Optional.ofNullable(ID_TO_ENUM.get(id));
    }
}
