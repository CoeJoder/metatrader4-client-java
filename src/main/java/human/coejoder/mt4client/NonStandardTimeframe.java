package human.coejoder.mt4client;

/**
 * The non-standard timeframes available for chart data in MetaTrader 4.  These periods can be used for working with
 * offline charts.
 *
 * @see <a href="https://docs.mql4.com/constants/chartconstants/enum_timeframes">enum_timeframes</a>
 */
@SuppressWarnings("unused")
public enum NonStandardTimeframe implements Timeframe {
    PERIOD_M2(2),
    PERIOD_M3(3),
    PERIOD_M4(4),
    PERIOD_M6(6),
    PERIOD_M10(10),
    PERIOD_M12(12),
    PERIOD_M20(20),
    PERIOD_H2(120),
    PERIOD_H3(180),
    PERIOD_H6(360),
    PERIOD_H8(480),
    PERIOD_H12(720);

    public final int minutes;

    NonStandardTimeframe(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }
}
