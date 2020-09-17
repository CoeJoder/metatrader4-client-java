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
    private static final String POINT = "point";
    private static final String DIGITS = "digits";
    private static final String VOLUME_MIN = "volume_min";
    private static final String VOLUME_STEP = "volume_step";
    private static final String VOLUME_MAX = "volume_max";
    private static final String TRADE_CONTRACT_SIZE = "trade_contract_size";
    private static final String TRADE_TICK_VALUE = "trade_tick_value";
    private static final String TRADE_TICK_SIZE = "trade_tick_size";
    private static final String TRADE_STOPS_LEVEL = "trade_stops_level";
    private static final String TRADE_FREEZE_LEVEL = "trade_freeze_level";
    private static final String SYMBOL = "symbol";

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
     * @param mt4      The {@link MT4Client} object.
     * @param response The basic symbol info as returned by the server.
     */
    Symbol(MT4Client mt4, JsonNode response) {
        this.mt4 = mt4;
        this.name = response.get(NAME).asText();
        this.point = response.get(POINT).asDouble();
        this.digits = response.get(DIGITS).asInt();
        this.volumeMin = response.get(VOLUME_MIN).asDouble();
        this.volumeStep = response.get(VOLUME_STEP).asDouble();
        this.volumeMax = response.get(VOLUME_MAX).asDouble();
        this.tradeContractSize = response.get(TRADE_CONTRACT_SIZE).asDouble();
        this.tradeTickValue = response.get(TRADE_TICK_VALUE).asDouble();
        this.tradeTickSize = response.get(TRADE_TICK_SIZE).asDouble();
        this.tradeStopsLevel = response.get(TRADE_STOPS_LEVEL).asInt();
        this.tradeFreezeLevel = response.get(TRADE_FREEZE_LEVEL).asInt();
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
        return new SymbolTick(mt4.getResponse(Request.GET_SYMBOL_TICK.build()
                .put(SYMBOL, name)));
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
