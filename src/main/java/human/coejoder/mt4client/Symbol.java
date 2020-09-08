package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    private static final String SYMBOL = "symbol";

    private final MT4Client mt4;

    /**
     * Symbol name.
     */
    public final String name;

    /**
     * Point size in the quote currency.
     */
    public final double pointSize;

    /**
     * Digits after decimal point.
     */
    public final int digits;

    /**
     * Lot size in the base currency.
     */
    public final double lotSize;

    /**
     * Tick value in the deposit currency.
     */
    public final double tickValue;

    /**
     * Tick size in points.
     */
    public final double tickSize;

    /**
     * Minimum permitted amount of a lot.
     */
    public final double minLot;

    /**
     * Step for changing lots.
     */
    public final double lotStep;

    /**
     * Maximum permitted amount of a lot.
     */
    public final double maxLot;

    /**
     * Initial margin requirements for 1 lot.
     */
    public final double marginInit;

    /**
     * Margin to maintain open orders calculated for 1 lot.
     */
    public final double marginMaintenance;

    /**
     * Hedged margin calculated for 1 lot.
     */
    public final double marginHedged;

    /**
     * Free margin required to open 1 lot for buying.
     */
    public final double marginRequired;

    /**
     * Stop level in points.
     */
    public final double stopLevel;

    /**
     * Order freeze level in points.
     */
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

    /**
     * Get the latest market prices of this symbol.
     *
     * @return The latest symbol tick.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public SymbolTick getTick() throws JsonProcessingException, MT4Exception {
        return new SymbolTick(mt4.getResponse(Request.GET_SYMBOL_TICK.build()
                .put(SYMBOL, name)));
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
