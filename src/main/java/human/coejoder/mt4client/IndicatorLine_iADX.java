package human.coejoder.mt4client;

/**
 * Indicator line identifiers used in iADX() indicator.
 */
public enum IndicatorLine_iADX {
    MAIN(0),
    PLUSDI(1),
    MINUSDI(2);

    public final int id;

    IndicatorLine_iADX(int id) {
        this.id = id;
    }
}
