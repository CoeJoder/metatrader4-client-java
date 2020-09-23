package human.coejoder.mt4client;

/**
 * MetaTrader 4 account properties which return a double.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/accountinformation#enum_account_info_double">ENUM_ACCOUNT_INFO_DOUBLE</a>
 */
public enum AccountInfoDouble {
    ACCOUNT_BALANCE(37),
    ACCOUNT_CREDIT(38),
    ACCOUNT_PROFIT(39),
    ACCOUNT_EQUITY(40),
    ACCOUNT_MARGIN(41),
    ACCOUNT_MARGIN_FREE(42),
    ACCOUNT_MARGIN_LEVEL(43),
    ACCOUNT_MARGIN_SO_CALL(45),
    ACCOUNT_MARGIN_SO_SO(46),
    ACCOUNT_MARGIN_INITIAL(48),         // deprecated
    ACCOUNT_MARGIN_MAINTENANCE(49),     // deprecated
    ACCOUNT_ASSETS(50),                 // deprecated
    ACCOUNT_LIABILITIES(51),            // deprecated
    ACCOUNT_COMMISSION_BLOCKED(52);     // deprecated

    public final int id;

    AccountInfoDouble(int id) {
        this.id = id;
    }
}
