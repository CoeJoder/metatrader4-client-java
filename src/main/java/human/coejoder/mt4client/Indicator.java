package human.coejoder.mt4client;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class Indicator {

    private static final String iAC = "iAC";
    private static final String iAD = "iAD";
    private static final String iADX = "iADX";
    private static final String iAlligator = "iAlligator";
    private static final String iAO = "iAO";
    private static final String iATR = "iATR";
    private static final String iBearsPower = "iBearsPower";
    private static final String iBands = "iBands";
    private static final String iBullsPower = "iBullsPower";
    private static final String iCCI = "iCCI";
    private static final String iDeMarker = "iDeMarker";
    private static final String iEnvelopes = "iEnvelopes";
    private static final String iForce = "iForce";
    private static final String iFractals = "iFractals";
    private static final String iGator = "iGator";
    private static final String iIchimoku = "iIchimoku";
    private static final String iBWMFI = "iBWMFI";
    private static final String iMomentum = "iMomentum";
    private static final String iMFI = "iMFI";
    private static final String iMA = "iMA";
    private static final String iOsMA = "iOsMA";
    private static final String iMACD = "iMACD";
    private static final String iOBV = "iOBV";
    private static final String iSAR = "iSAR";
    private static final String iRSI = "iRSI";
    private static final String iRVI = "iRVI";
    private static final String iStdDev = "iStdDev";
    private static final String iStochastic = "iStochastic";
    private static final String iWPR = "iWPR";

    /**
     * Calculates the Bill Williams' Accelerator/Decelerator oscillator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Bill Williams' Accelerator/Decelerator oscillator.
     * @see <a href="https://docs.mql4.com/indicators/iac">https://docs.mql4.com/indicators/iac</a>
     */
    public static Indicator iAC(String symbol, Timeframe timeframe, long shift) {
        return new Indicator(iAC, JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift));
    }

    /**
     * Calculates the Accumulation/Distribution indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Accumulation/Distribution indicator.
     * @see <a href="https://docs.mql4.com/indicators/iad">https://docs.mql4.com/indicators/iad</a>
     */
    public static Indicator iAD(String symbol, Timeframe timeframe, long shift) {
        return new Indicator(iAD, JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift));
    }

    /**
     * Calculates the Average Directional Movement Index indicator and returns its value.
     *
     * @param symbol        Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                      symbol.
     * @param timeframe     Timeframe. It can be any {@link StandardTimeframe}.
     * @param period        Averaging period for calculation.
     * @param applied_price Applied price. It can be any {@link AppliedPrice}.
     * @param mode          Indicator line index. It can be any {@link IndicatorLine_iADX}.
     * @param shift         Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                      given amount of periods ago).
     * @return Numerical value of the Average Directional Movement Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/iadx">https://docs.mql4.com/indicators/iadx</a>
     */
    public static Indicator iADX(String symbol, Timeframe timeframe, long period, AppliedPrice applied_price, IndicatorLine_iADX mode, long shift) {
        return new Indicator(iADX, JsonNodeFactory.instance.arrayNode(6)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(applied_price.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Alligator indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param jawPeriod    Blue line averaging period (Alligator's Jaw).
     * @param jawShift     Blue line shift relative to the chart.
     * @param teethPeriod  Red line averaging period (Alligator's Teeth).
     * @param teethShift   Red line shift relative to the chart.
     * @param lipsPeriod   Green line averaging period (Alligator's Lips).
     * @param lipsShift    Green line shift relative to the chart.
     * @param maMethod     MA method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param mode         Data source, identifier of the indicator line.  It can be any {@link
     *                     IndicatorLine_iAlligator}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Alligator indicator.
     * @see <a href="https://docs.mql4.com/indicators/ialligator">https://docs.mql4.com/indicators/ialligator</a>
     */
    public static Indicator iAlligator(String symbol, Timeframe timeframe, long jawPeriod, long jawShift, long teethPeriod, long teethShift, long lipsPeriod, long lipsShift, SmoothingMethod maMethod, AppliedPrice appliedPrice, IndicatorLine_iAlligator mode, long shift) {
        return new Indicator(iAlligator, JsonNodeFactory.instance.arrayNode(12)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(jawPeriod)
                .add(jawShift)
                .add(teethPeriod)
                .add(teethShift)
                .add(lipsPeriod)
                .add(lipsShift)
                .add(maMethod.id)
                .add(appliedPrice.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Awesome oscillator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Awesome oscillator indicator.
     * @see <a href="https://docs.mql4.com/indicators/iao">https://docs.mql4.com/indicators/iao</a>
     */
    public static Indicator iAO(String symbol, Timeframe timeframe, long shift) {
        return new Indicator(iAO, JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift));
    }

    /**
     * Calculates the Average True Range indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param period    Averaging period.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Average True Range indicator.
     * @see <a href="https://docs.mql4.com/indicators/iatr">https://docs.mql4.com/indicators/iatr</a>
     */
    public static Indicator iATR(String symbol, Timeframe timeframe, long period, long shift) {
        return new Indicator(iATR, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(shift));
    }

    /**
     * Calculates the Bears Power indicator and returns its value.Calculates the Bears Power indicator and returns its
     * value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bears Power indicator.
     * @see <a href="https://docs.mql4.com/indicators/ibearspower">https://docs.mql4.com/indicators/ibearspower</a>
     */
    public static Indicator iBearsPower(String symbol, Timeframe timeframe, long period, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iBearsPower, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Bollinger Bands® indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period to calculate the main line.
     * @param deviation    Number of standard deviations from the main line.
     * @param bandsShift   The indicator shift relative to the chart.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param mode         Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bollinger Bands® indicator.
     * @see <a href="https://docs.mql4.com/indicators/ibands">https://docs.mql4.com/indicators/ibands</a>
     */
    public static Indicator iBands(String symbol, Timeframe timeframe, long period, double deviation, double bandsShift, AppliedPrice appliedPrice, IndicatorLine_iBands mode, long shift) {
        return new Indicator(iBands, JsonNodeFactory.instance.arrayNode(9)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(deviation)
                .add(bandsShift)
                .add(appliedPrice.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Bulls Power indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period for calculation.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bulls Power indicator.
     * @see <a href="https://docs.mql4.com/indicators/ibullspower">https://docs.mql4.com/indicators/ibullspower</a>
     */
    public static Indicator iBullsPower(String symbol, Timeframe timeframe, long period, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iBullsPower, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Commodity Channel Index indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period for calculation.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Commodity Channel Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/icci">https://docs.mql4.com/indicators/icci</a>
     */
    public static Indicator iCCI(String symbol, Timeframe timeframe, long period, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iCCI, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the DeMarker indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param period    Averaging period for calculation.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the DeMarker indicator.
     * @see <a href="https://docs.mql4.com/indicators/idemarker">https://docs.mql4.com/indicators/idemarker</a>
     */
    public static Indicator iDeMarker(String symbol, Timeframe timeframe, long period, long shift) {
        return new Indicator(iDeMarker, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(shift));
    }

    /**
     * Calculates the Envelopes indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param maPeriod     Averaging period for calculation of the main line.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param maShift      MA shift. Indicator line offset relate to the chart by timeframe.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param deviation    Percent deviation from the main line.
     * @param mode         Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Envelopes indicator.
     * @see <a href="https://docs.mql4.com/indicators/ienvelopes">https://docs.mql4.com/indicators/ienvelopes</a>
     */
    public static Indicator iEnvelopes(String symbol, Timeframe timeframe, long maPeriod, SmoothingMethod maMethod, double maShift, AppliedPrice appliedPrice, double deviation, IndicatorLine_iBands mode, long shift) {
        return new Indicator(iEnvelopes, JsonNodeFactory.instance.arrayNode(9)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(maPeriod)
                .add(maMethod.id)
                .add(maShift)
                .add(appliedPrice.id)
                .add(deviation)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Force Index indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period for calculation.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Force Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/iforce">https://docs.mql4.com/indicators/iforce</a>
     */
    public static Indicator iForce(String symbol, Timeframe timeframe, long period, SmoothingMethod maMethod, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iForce, JsonNodeFactory.instance.arrayNode(6)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(maMethod.id)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Fractals indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param mode      Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Fractals indicator.
     * @see <a href="https://docs.mql4.com/indicators/ifractals">https://docs.mql4.com/indicators/ifractals</a>
     */
    public static Indicator iFractals(String symbol, Timeframe timeframe, IndicatorLine_iBands mode, long shift) {
        return new Indicator(iFractals, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Gator oscillator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param jawPeriod    Blue line averaging period (Alligator's Jaw).
     * @param jawShift     Blue line shift relative to the chart.
     * @param teethPeriod  Red line averaging period (Alligator's Teeth).
     * @param teethShift   Red line shift relative to the chart.
     * @param lipsPeriod   Green line averaging period (Alligator's Lips).
     * @param lipsShift    Green line shift relative to the chart.
     * @param maMethod     MA method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param mode         Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Gator oscillator.
     * @see <a href="https://docs.mql4.com/indicators/igator">https://docs.mql4.com/indicators/igator</a>
     */
    public static Indicator iGator(String symbol, Timeframe timeframe, long jawPeriod, long jawShift, long teethPeriod, long teethShift, long lipsPeriod, long lipsShift, SmoothingMethod maMethod, AppliedPrice appliedPrice, IndicatorLine_iBands mode, long shift) {
        return new Indicator(iGator, JsonNodeFactory.instance.arrayNode(12)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(jawPeriod)
                .add(jawShift)
                .add(teethPeriod)
                .add(teethShift)
                .add(lipsPeriod)
                .add(lipsShift)
                .add(maMethod.id)
                .add(appliedPrice.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Ichimoku Kinko Hyo indicator and returns its value.
     *
     * @param symbol      Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                    symbol.
     * @param timeframe   Timeframe. It can be any {@link StandardTimeframe}.
     * @param tenkanSen   Tenkan Sen averaging period.
     * @param kijunSen    Kijun Sen averaging period.
     * @param senkouSpanB Senkou SpanB averaging period.
     * @param mode        Source of data. It can be any {@link IndicatorLine_iIchimoku}.
     * @param shift       Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                    given amount of periods ago).
     * @return Numerical value of the Ichimoku Kinko Hyo indicator.
     * @see <a href="https://docs.mql4.com/indicators/iichimoku">https://docs.mql4.com/indicators/iichimoku</a>
     */
    public static Indicator iIchimoku(String symbol, Timeframe timeframe, long tenkanSen, long kijunSen, long senkouSpanB, IndicatorLine_iIchimoku mode, long shift) {
        return new Indicator(iIchimoku, JsonNodeFactory.instance.arrayNode(7)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(tenkanSen)
                .add(kijunSen)
                .add(senkouSpanB)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Market Facilitation Index indicator and returns its value..
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Market Facilitation Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/ibwmfi">https://docs.mql4.com/indicators/ibwmfi</a>
     */
    public static Indicator iBWMFI(String symbol, Timeframe timeframe, long shift) {
        return new Indicator(iBWMFI, JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift));
    }

    /**
     * Calculates the Momentum indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period (amount of bars) for calculation of price changes.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Momentum indicator.
     * @see <a href="https://docs.mql4.com/indicators/imomentum">https://docs.mql4.com/indicators/imomentum</a>
     */
    public static Indicator iMomentum(String symbol, Timeframe timeframe, long period, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iMomentum, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Money Flow Index indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param period    Period (amount of bars) for calculation of the indicator.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Money Flow Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/imfi">https://docs.mql4.com/indicators/imfi</a>
     */
    public static Indicator iMFI(String symbol, Timeframe timeframe, long period, long shift) {
        return new Indicator(iMFI, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(shift));
    }

    /**
     * Calculates the Moving Average indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param maPeriod     Averaging period for calculation.
     * @param maShift      MA shift. Indicators line offset relate to the chart by timeframe.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Moving Average indicator.
     * @see <a href="https://docs.mql4.com/indicators/ima">https://docs.mql4.com/indicators/ima</a>
     */
    public static Indicator iMA(String symbol, Timeframe timeframe, long maPeriod, long maShift, SmoothingMethod maMethod, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iMA, JsonNodeFactory.instance.arrayNode(7)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(maPeriod)
                .add(maShift)
                .add(maMethod.id)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Moving Average of Oscillator indicator and returns its value.
     *
     * @param symbol        Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                      symbol.
     * @param timeframe     Timeframe. It can be any {@link StandardTimeframe}.
     * @param fastEmaPeriod Fast EMA averaging period.
     * @param slowEmaPeriod Slow EMA averaging period.
     * @param signalPeriod  Signal line averaging period.
     * @param appliedPrice  Applied price. It can be any {@link AppliedPrice}.
     * @param shift         Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                      given amount of periods ago).
     * @return Numerical value of the Moving Average of Oscillator.
     * @see <a href="https://docs.mql4.com/indicators/iosma">https://docs.mql4.com/indicators/iosma</a>
     */
    public static Indicator iOsMA(String symbol, Timeframe timeframe, long fastEmaPeriod, long slowEmaPeriod, long signalPeriod, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iOsMA, JsonNodeFactory.instance.arrayNode(7)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(fastEmaPeriod)
                .add(slowEmaPeriod)
                .add(signalPeriod)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Moving Averages Convergence/Divergence indicator and returns its value.
     *
     * @param symbol        Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                      symbol.
     * @param timeframe     Timeframe. It can be any {@link StandardTimeframe}.
     * @param fastEmaPeriod Fast EMA averaging period.
     * @param slowEmaPeriod Slow EMA averaging period.
     * @param signalPeriod  Signal line averaging period.
     * @param appliedPrice  Applied price. It can be any {@link AppliedPrice}.
     * @param mode          Indicator line index. It can be any {@link IndicatorLine_iMACD}.
     * @param shift         Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                      given amount of periods ago).
     * @return Numerical value of the Moving Average of Oscillator indicator.
     * @see <a href="https://docs.mql4.com/indicators/imacd">https://docs.mql4.com/indicators/imacd</a>
     */
    public static Indicator iMACD(String symbol, Timeframe timeframe, long fastEmaPeriod, long slowEmaPeriod, long signalPeriod, AppliedPrice appliedPrice, IndicatorLine_iMACD mode, long shift) {
        return new Indicator(iMACD, JsonNodeFactory.instance.arrayNode(8)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(fastEmaPeriod)
                .add(slowEmaPeriod)
                .add(signalPeriod)
                .add(appliedPrice.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the On Balance Volume indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the On Balance Volume indicator.
     * @see <a href="https://docs.mql4.com/indicators/iobv">https://docs.mql4.com/indicators/iobv</a>
     */
    public static Indicator iOBV(String symbol, Timeframe timeframe, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iOBV, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Parabolic Stop and Reverse system indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param step      The step of price increment, usually 0.02.
     * @param maximum   The maximum step, usually 0.2.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Parabolic Stop and Reverse system indicator.
     * @see <a href="https://docs.mql4.com/indicators/isar">https://docs.mql4.com/indicators/isar</a>
     */
    public static Indicator iSAR(String symbol, Timeframe timeframe, double step, double maximum, long shift) {
        return new Indicator(iSAR, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(step)
                .add(maximum)
                .add(shift));
    }

    /**
     * Calculates the Relative Strength Index indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param period       Averaging period for calculation.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Relative Strength Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/irsi">https://docs.mql4.com/indicators/irsi</a>
     */
    public static Indicator iRSI(String symbol, Timeframe timeframe, long period, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iRSI, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Relative Vigor Index indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param period    Averaging period for calculation.
     * @param mode      Indicator line index. It can be any {@link IndicatorLine_iMACD}.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Relative Vigor Index indicator.
     * @see <a href="https://docs.mql4.com/indicators/irvi">https://docs.mql4.com/indicators/irvi</a>
     */
    public static Indicator iRVI(String symbol, Timeframe timeframe, long period, IndicatorLine_iMACD mode, long shift) {
        return new Indicator(iRVI, JsonNodeFactory.instance.arrayNode(5)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Standard Deviation indicator and returns its value.
     *
     * @param symbol       Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                     symbol.
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}.
     * @param maPeriod     Moving Average period.
     * @param maShift      Moving Average shift.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Standard Deviation indicator.
     * @see <a href="https://docs.mql4.com/indicators/istddev">https://docs.mql4.com/indicators/istddev</a>
     */
    public static Indicator iStdDev(String symbol, Timeframe timeframe, long maPeriod, long maShift, SmoothingMethod maMethod, AppliedPrice appliedPrice, long shift) {
        return new Indicator(iStdDev, JsonNodeFactory.instance.arrayNode(7)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(maPeriod)
                .add(maShift)
                .add(maMethod.id)
                .add(appliedPrice.id)
                .add(shift));
    }

    /**
     * Calculates the Stochastic Oscillator and returns its value.
     *
     * @param symbol     Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                   symbol.
     * @param timeframe  Timeframe. It can be any {@link StandardTimeframe}.
     * @param kPeriod    Period of the %K line.
     * @param dPeriod    Period of the %D line.
     * @param slowing    Slowing value.
     * @param method     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param priceField Price field parameter. Can be any {@link PriceField}.
     * @param mode       Indicator line index. It can be any {@link IndicatorLine_iMACD}.
     * @param shift      Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                   amount of periods ago).
     * @return Numerical value of the Stochastic Oscillator.
     * @see <a href="https://docs.mql4.com/indicators/istochastic">https://docs.mql4.com/indicators/istochastic</a>
     */
    public static Indicator iStochastic(String symbol, Timeframe timeframe, long kPeriod, long dPeriod, long slowing, SmoothingMethod method, PriceField priceField, IndicatorLine_iMACD mode, long shift) {
        return new Indicator(iStochastic, JsonNodeFactory.instance.arrayNode(9)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(kPeriod)
                .add(dPeriod)
                .add(slowing)
                .add(method.id)
                .add(priceField.id)
                .add(mode.id)
                .add(shift));
    }

    /**
     * Calculates the Larry Williams' Percent Range and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}.
     * @param period    Averaging period for calculation.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Larry Williams' Percent Range indicator.
     * @see <a href="https://docs.mql4.com/indicators/iwpr">https://docs.mql4.com/indicators/iwpr</a>
     */
    public static Indicator iWPR(String symbol, Timeframe timeframe, long period, long shift) {
        return new Indicator(iWPR, JsonNodeFactory.instance.arrayNode(4)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(shift));
    }

    private final String name;
    private final ArrayNode arguments;

    public Indicator(String name, ArrayNode arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    String getName() {
        return name;
    }

    ArrayNode getArguments() {
        return arguments;
    }
}
