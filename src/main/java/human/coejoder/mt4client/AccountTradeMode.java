package human.coejoder.mt4client;

import java.util.EnumSet;

/**
 * MetaTrader 4 account type.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_trade_mode">ENUM_ACCOUNT_TRADE_MODE</a>
 */
@SuppressWarnings("unused")
public enum AccountTradeMode {
    ACCOUNT_TRADE_MODE_DEMO(0),
    ACCOUNT_TRADE_MODE_CONTEST(1),
    ACCOUNT_TRADE_MODE_REAL(2);

    private static final AccountTradeMode[] COPY_OF_VALUES = values();

    public final int id;

    AccountTradeMode(int id) {
        this.id = id;
    }

    public static AccountTradeMode fromInt(int intCode) {
        for (AccountTradeMode mode : COPY_OF_VALUES) {
            if (intCode == mode.id) {
                return mode;
            }
        }
        throw new RuntimeException("Unknown trade mode: " + intCode);
    }
}
