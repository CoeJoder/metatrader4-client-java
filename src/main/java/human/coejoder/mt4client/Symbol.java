package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * A market symbol in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/constants/environment_state/marketinfoconstants">marketinfoconstants</a>
 */
public class Symbol {

    private static final String VOLUME_MIN = "volume_min";
    private static final String VOLUME_STEP = "volume_step";
    private static final String VOLUME_MAX = "volume_max";
    private static final String TRADE_CONTRACT_SIZE = "trade_contract_size";
    private static final String TRADE_TICK_VALUE = "trade_tick_value";
    private static final String TRADE_TICK_SIZE = "trade_tick_size";
    private static final String TRADE_STOPS_LEVEL = "trade_stops_level";
    private static final String TRADE_FREEZE_LEVEL = "trade_freeze_level";
    private static final String SYMBOL = "symbol";
    private static final String PROPERTY_ID = "property_id";

    private final MT4Client mt4;
    private final String name;
    private final double point;
    private final int digits;
    private final double volumeMin;
    private final double volumeStep;
    private final double volumeMax;
    private final double tradeContractSize;
    private final double tradeTickValue;
    private final double tradeTickSize;
    private final int tradeStopsLevel;
    private final int tradeFreezeLevel;

    /**
     * Package-private constructor.
     *
     * @param mt4               The {@link MT4Client} object.
     * @param name              Symbol name.
     * @param point             Point size in the quote currency.
     * @param digits            Digits after decimal point.
     * @param volumeMin         Minimal volume for a deal.
     * @param volumeStep        Minimal volume change step for deal execution
     * @param volumeMax         Maximal volume for a deal.
     * @param tradeContractSize Trade contract size in the base currency.
     * @param tradeTickValue    Tick value in the deposit currency.
     * @param tradeTickSize     Tick size in points.
     * @param tradeStopsLevel   Stop level in points.
     * @param tradeFreezeLevel  Order freeze level in points.
     */
    @JsonCreator
    Symbol(@JacksonInject MT4Client mt4,
           String name,
           double point,
           int digits,
           @JsonProperty(VOLUME_MIN) double volumeMin,
           @JsonProperty(VOLUME_STEP) double volumeStep,
           @JsonProperty(VOLUME_MAX) double volumeMax,
           @JsonProperty(TRADE_CONTRACT_SIZE) double tradeContractSize,
           @JsonProperty(TRADE_TICK_VALUE) double tradeTickValue,
           @JsonProperty(TRADE_TICK_SIZE) double tradeTickSize,
           @JsonProperty(TRADE_STOPS_LEVEL) int tradeStopsLevel,
           @JsonProperty(TRADE_FREEZE_LEVEL) int tradeFreezeLevel) {
        this.mt4 = mt4;
        this.name = name;
        this.point = point;
        this.digits = digits;
        this.volumeMin = volumeMin;
        this.volumeStep = volumeStep;
        this.volumeMax = volumeMax;
        this.tradeContractSize = tradeContractSize;
        this.tradeTickValue = tradeTickValue;
        this.tradeTickSize = tradeTickSize;
        this.tradeStopsLevel = tradeStopsLevel;
        this.tradeFreezeLevel = tradeFreezeLevel;
    }

    /**
     * Symbol name.
     *
     * @return the symbol name, eg. EURUSD
     */
    public String getName() {
        return name;
    }

    /**
     * Point size in the quote currency.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_POINT)</code>
     */
    public double getPoint() {
        return point;
    }

    /**
     * Digits after decimal point.
     *
     * @return <code>SymbolInfoInteger(name, SYMBOL_DIGITS)</code>
     */
    public int getDigits() {
        return digits;
    }

    /**
     * Minimal volume for a deal.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_VOLUME_MIN)</code>
     */
    public double getVolumeMin() {
        return volumeMin;
    }

    /**
     * Minimal volume change step for deal execution
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_VOLUME_STEP)</code>
     */
    public double getVolumeStep() {
        return volumeStep;
    }

    /**
     * Maximal volume for a deal.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_VOLUME_MAX)</code>
     */
    public double getVolumeMax() {
        return volumeMax;
    }

    /**
     * Trade contract size in the base currency.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_TRADE_CONTRACT_SIZE)</code>
     */
    public double getTradeContractSize() {
        return tradeContractSize;
    }

    /**
     * Tick value in the deposit currency.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_TRADE_TICK_VALUE)</code>
     */
    public double getTradeTickValue() {
        return tradeTickValue;
    }

    /**
     * Tick size in points.
     *
     * @return <code>SymbolInfoDouble(name, SYMBOL_TRADE_TICK_SIZE)</code>
     */
    public double getTradeTickSize() {
        return tradeTickSize;
    }

    /**
     * Stop level in points.
     *
     * @return <code>SymbolInfoInteger(name, SYMBOL_TRADE_STOPS_LEVEL)</code>
     */
    public int getTradeStopsLevel() {
        return tradeStopsLevel;
    }

    /**
     * Order freeze level in points.
     *
     * @return <code>SymbolInfoInteger(name, SYMBOL_TRADE_FREEZE_LEVEL)</code>
     */
    public int getTradeFreezeLevel() {
        return tradeFreezeLevel;
    }

    /**
     * Get the latest market prices of this symbol.
     *
     * @return The latest symbol tick.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public SymbolTick getTick() throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_SYMBOL_TICK.build()
                .put(SYMBOL, name), SymbolTick.class);
    }

    private int getSymbolInfoInteger(SymbolInfoInteger prop) throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_SYMBOL_INFO_INTEGER.build()
                .put(SYMBOL, name)
                .put(PROPERTY_ID, prop.id), Integer.class);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", point=" + point +
                ", digits=" + digits +
                ", volumeMin=" + volumeMin +
                ", volumeStep=" + volumeStep +
                ", volumeMax=" + volumeMax +
                ", tradeContractSize=" + tradeContractSize +
                ", tradeTickValue=" + tradeTickValue +
                ", tradeTickSize=" + tradeTickSize +
                ", tradeStopsLevel=" + tradeStopsLevel +
                ", tradeFreezeLevel=" + tradeFreezeLevel +
                '}';
    }
}
