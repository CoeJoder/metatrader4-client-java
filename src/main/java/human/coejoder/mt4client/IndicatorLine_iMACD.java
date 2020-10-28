package human.coejoder.mt4client;

/**
 * Indicator line identifiers used in iMACD(), iRVI() and iStochastic() indicators.
 */
public enum IndicatorLine_iMACD {
    MAIN(0),
    SIGNAL(1);

    public final int id;

    IndicatorLine_iMACD(int id) {
        this.id = id;
    }
}
