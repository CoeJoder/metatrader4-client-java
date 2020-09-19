package human.coejoder.mt4client;

/**
 * MetaTrader 4 market symbol properties which return an integer.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants#enum_symbol_info_integer">ENUM_SYMBOL_INFO_INTEGER</a>
 */
@SuppressWarnings("unused")
public enum SymbolInfoInteger {
    SYMBOL_SELECT(0),
    SYMBOL_VISIBLE(76),
    SYMBOL_SESSION_DEALS(56),               // MQL5 only
    SYMBOL_SESSION_BUY_ORDERS(60),          // MQL5 only
    SYMBOL_SESSION_SELL_ORDERS(62),         // MQL5 only
    SYMBOL_VOLUME(10),                      // MQL5 only
    SYMBOL_VOLUMEHIGH(11),                  // MQL5 only
    SYMBOL_VOLUMELOW(12),                   // MQL5 only
    SYMBOL_TIME(15),
    SYMBOL_DIGITS(17),
    SYMBOL_SPREAD_FLOAT(41),
    SYMBOL_SPREAD(18),
    SYMBOL_TRADE_CALC_MODE(29),
    SYMBOL_TRADE_MODE(30),
    SYMBOL_START_TIME(51),
    SYMBOL_EXPIRATION_TIME(52),
    SYMBOL_TRADE_STOPS_LEVEL(31),
    SYMBOL_TRADE_FREEZE_LEVEL(32),
    SYMBOL_TRADE_EXEMODE(33),
    SYMBOL_SWAP_MODE(37),
    SYMBOL_SWAP_ROLLOVER3DAYS(40),
    SYMBOL_EXPIRATION_MODE(49),             // MQL5 only
    SYMBOL_FILLING_MODE(50),                // MQL5 only
    SYMBOL_ORDER_MODE(71);                  // MQL5 only

    public final int id;

    SymbolInfoInteger(int id) {
        this.id = id;
    }
}
