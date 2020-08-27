package human.coejoder.mt4client.api;

/**
 * MetaTrader 4 account's stop out mode.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_stopout_mode">ENUM_ACCOUNT_STOPOUT_MODE</a>
 */
@SuppressWarnings("unused")
public enum AccountStopoutMode {
    ACCOUNT_STOPOUT_MODE_PERCENT(0),
    ACCOUNT_STOPOUT_MODE_MONEY(1);

    public final int id;

    AccountStopoutMode(int id) {
        this.id = id;
    }
}
