package human.coejoder.mt4client;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Indicator extends Supplier<ArrayNode> {

    /**
     * Calculates the Bill Williams' Accelerator/Decelerator oscillator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any of {@link StandardTimeframe ENUM_TIMEFRAMES} enumeration values. 0
     *                  means the current chart timeframe.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Bill Williams' Accelerator/Decelerator oscillator.
     * @see <a href="https://docs.mql4.com/indicators/iac">https://docs.mql4.com/indicators/iac</a>
     */
    static Indicator iAC(String symbol, Timeframe timeframe, long shift) {
        return () -> JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift);
    }

    /**
     * Calculates the Accumulation/Distribution indicator and returns its value.
     *
     * @param symbol    Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                  symbol.
     * @param timeframe Timeframe. It can be any of {@link StandardTimeframe ENUM_TIMEFRAMES} enumeration values. 0
     *                  means the current chart timeframe.
     * @param shift     Index of the value taken from the indicator buffer (shift relative to the current bar the given
     *                  amount of periods ago).
     * @return Numerical value of the Accumulation/Distribution indicator.
     * @see <a href="https://docs.mql4.com/indicators/iad">https://docs.mql4.com/indicators/iad</a>
     */
    static Indicator iAD(String symbol, Timeframe timeframe, long shift) {
        return () -> JsonNodeFactory.instance.arrayNode(3)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(shift);
    }

    /**
     * Calculates the Average Directional Movement Index indicator and returns its value.
     *
     * @param symbol        Symbol name on the data of which the indicator will be calculated. NULL means the current
     *                      symbol.
     * @param timeframe     Timeframe. It can be any of {@link StandardTimeframe ENUM_TIMEFRAMES} enumeration values. 0
     *                      means the current chart timeframe.
     * @param period        Averaging period for calculation.
     * @param applied_price Applied price. It can be any of {@link AppliedPrice} enumeration values.
     * @param arg4
     * @param arg5
     * @return
     */
    static Indicator iADX(String symbol, Timeframe timeframe, long period, AppliedPrice applied_price, long arg4, long arg5) {
        return () -> JsonNodeFactory.instance.arrayNode(6)
                .add(symbol)
                .add(timeframe.getMinutes())
                .add(period)
                .add(applied_price.id)
                .add(arg4)
                .add(arg5);
    }

    static Indicator iAlligator(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, long arg8, long arg9, long arg10, long arg11) {
        return () -> JsonNodeFactory.instance.arrayNode(12)
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
                .add(arg11);
    }

    static Indicator iAO(String arg0, long arg1, long arg2) {
        return () -> JsonNodeFactory.instance.arrayNode(3)
                .add(arg0)
                .add(arg1)
                .add(arg2);
    }

    static Indicator iATR(String arg0, long arg1, long arg2, long arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }

    static Indicator iBearsPower(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iBands(String arg0, long arg1, long arg2, double arg3, double arg4, long arg5, long arg6, long arg7, long arg8) {
        return () -> JsonNodeFactory.instance.arrayNode(9)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7)
                .add(arg8);
    }

    static Indicator iBullsPower(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iCCI(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iDeMarker(String arg0, long arg1, long arg2, long arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }

    static Indicator iEnvelopes(String arg0, long arg1, long arg2, double arg3, double arg4, long arg5, double arg6, long arg7, long arg8) {
        return () -> JsonNodeFactory.instance.arrayNode(9)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7)
                .add(arg8);
    }

    static Indicator iForce(String arg0, long arg1, long arg2, double arg3, double arg4, long arg5) {
        return () -> JsonNodeFactory.instance.arrayNode(6)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5);
    }

    static Indicator iFractals(String arg0, long arg1, long arg2, double arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }

    static Indicator iGator(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, long arg8, long arg9, long arg10, long arg11) {
        return () -> JsonNodeFactory.instance.arrayNode(12)
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
                .add(arg11);
    }

    static Indicator iIchimoku(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return () -> JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6);
    }

    static Indicator iBWMFI(String arg0, long arg1, long arg2) {
        return () -> JsonNodeFactory.instance.arrayNode(3)
                .add(arg0)
                .add(arg1)
                .add(arg2);
    }

    static Indicator iMomentum(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iMFI(String arg0, long arg1, long arg2, long arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }

    static Indicator iMA(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return () -> JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6);
    }

    static Indicator iOsMA(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return () -> JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6);
    }

    static Indicator iMACD(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7) {
        return () -> JsonNodeFactory.instance.arrayNode(8)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7);
    }

    static Indicator iOBV(String arg0, long arg1, long arg2, long arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }

    static Indicator iSAR(String arg0, long arg1, double arg2, double arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iRSI(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iRVI(String arg0, long arg1, long arg2, long arg3, long arg4) {
        return () -> JsonNodeFactory.instance.arrayNode(5)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4);
    }

    static Indicator iStdDev(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return () -> JsonNodeFactory.instance.arrayNode(7)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6);
    }

    static Indicator iStochastic(String arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, long arg8) {
        return () -> JsonNodeFactory.instance.arrayNode(9)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3)
                .add(arg4)
                .add(arg5)
                .add(arg6)
                .add(arg7)
                .add(arg8);
    }

    static Indicator iWPR(String arg0, long arg1, long arg2, long arg3) {
        return () -> JsonNodeFactory.instance.arrayNode(4)
                .add(arg0)
                .add(arg1)
                .add(arg2)
                .add(arg3);
    }
}
