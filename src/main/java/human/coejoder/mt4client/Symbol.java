package human.coejoder.mt4client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A market symbol in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants">marketinfoconstants</a>
 */
public class Symbol {

    private static final String NAME = "name";
    private static final String POINT_SIZE = "point_size";
    private static final String DIGITS = "digits";
    private static final String LOT_SIZE = "lot_size";
    private static final String TICK_VALUE = "tick_value";
    private static final String TICK_SIZE = "tick_size";
    private static final String MIN_LOT = "min_lot";
    private static final String LOT_STEP = "lot_step";
    private static final String MAX_LOT = "max_lot";
    private static final String MARGIN_INIT = "margin_init";
    private static final String MARGIN_MAINTENANCE = "margin_maintenance";
    private static final String MARGIN_HEDGED = "margin_hedged";
    private static final String MARGIN_REQUIRED = "margin_required";
    private static final String STOP_LEVEL = "stop_level";
    private static final String FREEZE_LEVEL = "freeze_level";

    private final MT4Client mt4;
    public final String name;
    public final double pointSize;
    public final int digits;
    public final double lotSize;
    public final double tickValue;
    public final double tickSize;
    public final double minLot;
    public final double lotStep;
    public final double maxLot;
    public final double marginInit;
    public final double marginMaintenance;
    public final double marginHedged;
    public final double marginRequired;
    public final double stopLevel;
    public final double freezeLevel;

    /**
     * Package-private constructor.
     *
     * @param mt4      The {@link MT4Client} object.
     * @param response The basic symbol info as returned by the server.
     */
    Symbol(MT4Client mt4, JsonNode response) {
        this.mt4 = mt4;
        this.name = response.get(NAME).asText();
        this.pointSize = response.get(POINT_SIZE).asDouble();
        this.digits = response.get(DIGITS).asInt();
        this.lotSize = response.get(LOT_SIZE).asDouble();
        this.tickValue = response.get(TICK_VALUE).asDouble();
        this.tickSize = response.get(TICK_SIZE).asDouble();
        this.minLot = response.get(MIN_LOT).asDouble();
        this.lotStep = response.get(LOT_STEP).asDouble();
        this.maxLot = response.get(MAX_LOT).asDouble();
        this.marginInit = response.get(MARGIN_INIT).asDouble();
        this.marginMaintenance = response.get(MARGIN_MAINTENANCE).asDouble();
        this.marginHedged = response.get(MARGIN_HEDGED).asDouble();
        this.marginRequired = response.get(MARGIN_REQUIRED).asDouble();
        this.stopLevel = response.get(STOP_LEVEL).asDouble();
        this.freezeLevel = response.get(FREEZE_LEVEL).asDouble();
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", pointSize=" + pointSize +
                ", digits=" + digits +
                ", lotSize=" + lotSize +
                ", tickValue=" + tickValue +
                ", tickSize=" + tickSize +
                ", minLot=" + minLot +
                ", lotStep=" + lotStep +
                ", maxLot=" + maxLot +
                ", marginInit=" + marginInit +
                ", marginMaintenance=" + marginMaintenance +
                ", marginHedged=" + marginHedged +
                ", marginRequired=" + marginRequired +
                ", stopLevel=" + stopLevel +
                ", freezeLevel=" + freezeLevel +
                '}';
    }
}
