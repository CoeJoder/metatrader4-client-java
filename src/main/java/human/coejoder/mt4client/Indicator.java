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
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
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
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
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
     * @param timeframe     Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
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
     * <a href="https://docs.mql4.com/indicators/ialligator">https://docs.mql4.com/indicators/ialligator</a>
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
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Awesome oscillator indicator.
     * <a href="https://docs.mql4.com/indicators/iao">https://docs.mql4.com/indicators/iao</a>
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
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period    Averaging period.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Average True Range indicator.
     * <a href="https://docs.mql4.com/indicators/iatr">https://docs.mql4.com/indicators/iatr</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period       Averaging period.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bears Power indicator.
     * <a href="https://docs.mql4.com/indicators/ibearspower">https://docs.mql4.com/indicators/ibearspower</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period       Averaging period to calculate the main line.
     * @param deviation    Number of standard deviations from the main line.
     * @param bandsShift   The indicator shift relative to the chart.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param mode         Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bollinger Bands® indicator.
     * <a href="https://docs.mql4.com/indicators/ibands">https://docs.mql4.com/indicators/ibands</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period       Averaging period for calculation.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Bulls Power indicator.
     * <a href="https://docs.mql4.com/indicators/ibullspower">https://docs.mql4.com/indicators/ibullspower</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period       Averaging period for calculation.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Commodity Channel Index indicator.
     * <a href="https://docs.mql4.com/indicators/icci">https://docs.mql4.com/indicators/icci</a>
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
     * @param timeframe Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period    Averaging period for calculation.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the DeMarker indicator.
     * <a href="https://docs.mql4.com/indicators/idemarker">https://docs.mql4.com/indicators/idemarker</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param maPeriod     Averaging period for calculation of the main line.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param maShift      MA shift. Indicator line offset relate to the chart by timeframe.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param deviation    Percent deviation from the main line.
     * @param mode         Indicator line index. It can be any {@link IndicatorLine_iBands}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Envelopes indicator.
     * <a href="https://docs.mql4.com/indicators/ienvelopes">https://docs.mql4.com/indicators/ienvelopes</a>
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
     * @param timeframe    Timeframe. It can be any {@link StandardTimeframe}. 0 means the current chart timeframe.
     * @param period       Averaging period for calculation.
     * @param maMethod     Moving Average method. It can be any {@link SmoothingMethod}.
     * @param appliedPrice Applied price. It can be any {@link AppliedPrice}.
     * @param shift        Index of the value taken from the indicator buffer (shift relative to the current bar the
     *                     given amount of periods ago).
     * @return Numerical value of the Force Index indicator.
     * <a href="https://docs.mql4.com/indicators/iforce">https://docs.mql4.com/indicators/iforce</a>
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

    public static Indicator iFractals(String arg0, long arg1, long arg2, double arg3) {
        return new Indicator(iFractals, JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3));
    }

    public static Indicator iGator(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, long arg8, long arg9, long arg10, long arg11) {
        return new Indicator(iGator, JsonNodeFactory.instance.arrayNode(12)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7)
                .add(arg8)
                .add(arg9)
                .add(arg10)
                .add(arg11));
    }

    public static Indicator iIchimoku(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return new Indicator(iIchimoku, JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6));
    }

    public static Indicator iBWMFI(String arg0, long arg1, long arg2) {
        return new Indicator(iBWMFI, JsonNodeFactory.instance.arrayNode(3)
                .add(arg0)
                .add(arg1)
                .add(arg2));
    }

    public static Indicator iMomentum(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return new Indicator(iMomentum, JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4));
    }

    public static Indicator iMFI(String arg0, long arg1, long arg2, long arg3) {
        return new Indicator(iMFI, JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3));
    }

    public static Indicator iMA(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return new Indicator(iMA, JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6));
    }

    public static Indicator iOsMA(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return new Indicator(iOsMA, JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6));
    }

    public static Indicator iMACD(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7) {
        return new Indicator(iMACD, JsonNodeFactory.instance.arrayNode(8)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7));
    }

    public static Indicator iOBV(String arg0, long arg1, long arg2, long arg3) {
        return new Indicator(iOBV, JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3));
    }

    public static Indicator iSAR(String arg0, long arg1, double arg2, double arg3, long arg4) {
        return new Indicator(iSAR, JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4));
    }

    public static Indicator iRSI(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return new Indicator(iRSI, JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4));
    }

    public static Indicator iRVI(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return new Indicator(iRVI, JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4));
    }

    public static Indicator iStdDev(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return new Indicator(iStdDev, JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6));
    }

    public static Indicator iStochastic(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, long arg8) {
        return new Indicator(iStochastic, JsonNodeFactory.instance.arrayNode(9)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7)
                .add(arg8));
    }

    public static Indicator iWPR(String arg0, long arg1, long arg2, long arg3) {
        return new Indicator(iWPR, JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3));
    }

    private final String name;
    private final ArrayNode arguments;

    public Indicator(String name, ArrayNode arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public ArrayNode getArguments() {
        return arguments;
    }
}
