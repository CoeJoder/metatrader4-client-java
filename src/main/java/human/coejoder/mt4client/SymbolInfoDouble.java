package human.coejoder.mt4client;

/**
 * MetaTrader 4 market symbol properties which return a double.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants#enum_symbol_info_double">ENUM_SYMBOL_INFO_DOUBLE</a>
 */
public enum SymbolInfoDouble {
    SYMBOL_BID(1),
    SYMBOL_BIDHIGH(2),                      // MQL 5 only
    SYMBOL_BIDLOW(3),                       // MQL 5 only
    SYMBOL_ASK(4),
    SYMBOL_ASKHIGH(5),                      // MQL 5 only
    SYMBOL_ASKLOW(6),                       // MQL 5 only
    SYMBOL_LAST(7),                         // MQL 5 only
    SYMBOL_LASTHIGH(8),                     // MQL 5 only
    SYMBOL_LASTLOW(9),                      // MQL 5 only
    SYMBOL_POINT(16),
    SYMBOL_TRADE_TICK_VALUE(26),
    SYMBOL_TRADE_TICK_VALUE_PROFIT(53),     // MQL 5 only
    SYMBOL_TRADE_TICK_VALUE_LOSS(54),       // MQL 5 only
    SYMBOL_TRADE_TICK_SIZE(27),
    SYMBOL_TRADE_CONTRACT_SIZE(28),
    SYMBOL_VOLUME_MIN(34),
    SYMBOL_VOLUME_MAX(35),
    SYMBOL_VOLUME_STEP(36),
    SYMBOL_VOLUME_LIMIT(55),                // MQL 5 only
    SYMBOL_SWAP_LONG(38),
    SYMBOL_SWAP_SHORT(39),
    SYMBOL_MARGIN_INITIAL(42),
    SYMBOL_MARGIN_MAINTENANCE(43),
    SYMBOL_MARGIN_LONG(44),                 // MQL 5 only
    SYMBOL_MARGIN_SHORT(45),                // MQL 5 only
    SYMBOL_MARGIN_LIMIT(46),                // MQL 5 only
    SYMBOL_MARGIN_STOP(47),                 // MQL 5 only
    SYMBOL_MARGIN_STOPLIMIT(48),            // MQL 5 only
    SYMBOL_SESSION_VOLUME(57),              // MQL 5 only
    SYMBOL_SESSION_TURNOVER(58),            // MQL 5 only
    SYMBOL_SESSION_INTEREST(59),            // MQL 5 only
    SYMBOL_SESSION_BUY_ORDERS_VOLUME(61),   // MQL 5 only
    SYMBOL_SESSION_SELL_ORDERS_VOLUME(63),  // MQL 5 only
    SYMBOL_SESSION_OPEN(64),                // MQL 5 only
    SYMBOL_SESSION_CLOSE(65),               // MQL 5 only
    SYMBOL_SESSION_AW(66),                  // MQL 5 only
    SYMBOL_SESSION_PRICE_SETTLEMENT(67),    // MQL 5 only
    SYMBOL_SESSION_PRICE_LIMIT_MIN(68),     // MQL 5 only
    SYMBOL_SESSION_PRICE_LIMIT_MAX(69);     // MQL 5 only

    public final int id;

    SymbolInfoDouble(int id) {
        this.id = id;
    }
}
