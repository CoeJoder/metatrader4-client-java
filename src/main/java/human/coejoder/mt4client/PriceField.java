package human.coejoder.mt4client;

/**
 * Price field for the iStochastic() indicator.
 */
public enum PriceField {
    LOW_HIGH(0),
    CLOSE_CLOSE(1);

    public final int id;

    PriceField(int id) {
        this.id = id;
    }
}
