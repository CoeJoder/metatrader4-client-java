package human.coejoder.mt4client;

/**
 * MetaTrader 4 account properties which return an integer.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_info_integer">ENUM_ACCOUNT_INFO_INTEGER</a>
 */
public enum AccountInfoInteger {
    ACCOUNT_LOGIN(0),
    ACCOUNT_TRADE_MODE(32),
    ACCOUNT_LEVERAGE(35),
    ACCOUNT_LIMIT_ORDERS(47),
    ACCOUNT_MARGIN_SO_MODE(44),
    ACCOUNT_TRADE_ALLOWED(33),
    ACCOUNT_TRADE_EXPERT(34);

    public final int id;

    AccountInfoInteger(int id) {
        this.id = id;
    }
}
