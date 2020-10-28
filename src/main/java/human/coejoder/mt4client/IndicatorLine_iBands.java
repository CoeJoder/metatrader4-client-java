package human.coejoder.mt4client;

/**
 * Indicator line identifiers used in iBands(), iEnvelopes(), iEnvelopesOnArray(), iFractals() and iGator() indicators.
 */
public enum IndicatorLine_iBands {
    MAIN(0),
    UPPER(1),
    LOWER(2);

    public final int id;

    IndicatorLine_iBands(int id) {
        this.id = id;
    }
}
