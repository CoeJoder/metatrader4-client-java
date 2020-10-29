package human.coejoder.mt4client;

/**
 * Ichimoku Kinko Hyo identifiers used in iIchimoku() indicator call as source of requested data.
 */
public enum IndicatorLine_iIchimoku {
    TENKANSEN(1),
    KIJUNSEN(2),
    SENKOUSPANA(3),
    SENKOUSPANB(4),
    CHIKOUSPAN(5);

    public final int id;

    IndicatorLine_iIchimoku(int id) {
        this.id = id;
    }
}
