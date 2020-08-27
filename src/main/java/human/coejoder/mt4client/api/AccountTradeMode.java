package human.coejoder.mt4client.api;

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

    public final int id;

    AccountTradeMode(int id) {
        this.id = id;
    }
}
