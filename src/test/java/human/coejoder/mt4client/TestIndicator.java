package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Integration tests for {@link Indicator indicator functions}.
 */
public class TestIndicator extends TestBase {

    private String symbol;
    private Timeframe timeframe;
    private long shift;
    private long period;
    private AppliedPrice appliedPrice;
    private IndicatorLine_iADX mode_iADX;
    private IndicatorLine_iAlligator mode_iAlligator;
    private IndicatorLine_iBands mode_iBands;
    private IndicatorLine_iIchimoku mode_iIchimoku;
    private IndicatorLine_iMACD mode_iMACD;
    private long jawPeriod;
    private long jawShift;
    private long teethPeriod;
    private long teethShift;
    private long lipsPeriod;
    private long lipsShift;
    private SmoothingMethod maMethod;
    private long deviation;
    private long bandsShift;
    private long maPeriod;
    private long maShift;
    private long tenkanSen;
    private long kijunSen;
    private long senkouSpanB;
    private long fastEmaPeriod;
    private long slowEmaPeriod;
    private long signalPeriod;
    private double step;
    private double maximum;
    private long kPeriod;
    private long dPeriod;
    private long slowing;
    private PriceField priceField;

    @BeforeClass
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        super.beforeClass();

        // indicator argument values, pulled from the MQL documentation examples
        // see: https://docs.mql4.com/indicators
        symbol = mt4.getSymbolNames().iterator().next();
        timeframe = StandardTimeframe.PERIOD_H1;
        shift = 1;
        period = 14;
        appliedPrice = AppliedPrice.PRICE_HIGH;
        mode_iADX = IndicatorLine_iADX.MAIN;
        mode_iAlligator = IndicatorLine_iAlligator.GATORJAW;
        mode_iBands = IndicatorLine_iBands.LOWER;
        mode_iIchimoku = IndicatorLine_iIchimoku.TENKANSEN;
        mode_iMACD = IndicatorLine_iMACD.SIGNAL;
        jawPeriod = 13;
        jawShift = 8;
        teethPeriod = 8;
        teethShift = 5;
        lipsPeriod = 5;
        lipsShift = 3;
        maMethod = SmoothingMethod.SMMA;
        deviation = 2;
        bandsShift = 0;
        maPeriod = 13;
        maShift = 10;
        tenkanSen = 9;
        kijunSen = 26;
        senkouSpanB = 52;
        fastEmaPeriod = 12;
        slowEmaPeriod = 26;
        signalPeriod = 9;
        step = 0.02;
        maximum = 0.2;
        kPeriod = 5;
        dPeriod = 3;
        slowing = 3;
        priceField = PriceField.LOW_HIGH;
    }

    @DataProvider
    public Object[][] indicators() {
        return new Object[][] {
                new Object[] {Indicator.iAC(symbol, timeframe, shift)},
                new Object[] {Indicator.iAD(symbol, timeframe, shift)},
                new Object[] {Indicator.iADX(symbol, timeframe, period, appliedPrice, mode_iADX, shift)},
                new Object[] {Indicator.iAlligator(symbol, timeframe, jawPeriod, jawShift, teethPeriod, teethShift, lipsPeriod, lipsShift, maMethod, appliedPrice, mode_iAlligator, shift)},
                new Object[] {Indicator.iAO(symbol, timeframe, shift)},
                new Object[] {Indicator.iATR(symbol, timeframe, period, shift)},
                new Object[] {Indicator.iBearsPower(symbol, timeframe, period, appliedPrice, shift)},
                new Object[] {Indicator.iBands(symbol, timeframe, period, deviation, bandsShift, appliedPrice, mode_iBands, shift)},
                new Object[] {Indicator.iBullsPower(symbol, timeframe, period, appliedPrice, shift)},
                new Object[] {Indicator.iCCI(symbol, timeframe, period, appliedPrice, shift)},
                new Object[] {Indicator.iDeMarker(symbol, timeframe, period, shift)},
                new Object[] {Indicator.iEnvelopes(symbol, timeframe, maPeriod, maMethod, maShift, appliedPrice, deviation, mode_iBands, shift)},
                new Object[] {Indicator.iForce(symbol, timeframe, period, maMethod, appliedPrice, shift)},
                new Object[] {Indicator.iFractals(symbol, timeframe, mode_iBands, shift)},
                new Object[] {Indicator.iGator(symbol, timeframe, jawPeriod, jawShift, teethPeriod, teethShift, lipsPeriod, lipsShift, maMethod, appliedPrice, mode_iBands, shift)},
                new Object[] {Indicator.iIchimoku(symbol, timeframe, tenkanSen, kijunSen, senkouSpanB, mode_iIchimoku, shift)},
                new Object[] {Indicator.iBWMFI(symbol, timeframe, shift)},
                new Object[] {Indicator.iMomentum(symbol, timeframe, period, appliedPrice, shift)},
                new Object[] {Indicator.iMFI(symbol, timeframe, period, shift)},
                new Object[] {Indicator.iMA(symbol, timeframe, maPeriod, maShift, maMethod, appliedPrice, shift)},
                new Object[] {Indicator.iOsMA(symbol, timeframe, fastEmaPeriod, slowEmaPeriod, signalPeriod, appliedPrice, shift)},
                new Object[] {Indicator.iMACD(symbol, timeframe, fastEmaPeriod, slowEmaPeriod, signalPeriod, appliedPrice, mode_iMACD, shift)},
                new Object[] {Indicator.iOBV(symbol, timeframe, appliedPrice, shift)},
                new Object[] {Indicator.iSAR(symbol, timeframe, step, maximum, shift)},
                new Object[] {Indicator.iRSI(symbol, timeframe, period, appliedPrice, shift)},
                new Object[] {Indicator.iRVI(symbol, timeframe, period, mode_iMACD, shift)},
                new Object[] {Indicator.iStdDev(symbol, timeframe, maPeriod, maShift, maMethod, appliedPrice, shift)},
                new Object[] {Indicator.iStochastic(symbol, timeframe, kPeriod, dPeriod, slowing, maMethod, priceField, mode_iMACD, shift)},
                new Object[] {Indicator.iWPR(symbol, timeframe, period, shift)}
        };
    }

    @Test(dataProvider = "indicators")
    public void testIndicator(Indicator func) throws JsonProcessingException, MT4Exception {
        double results = mt4.runIndicator(func);
        LOG.trace(String.format("%s(%s) = %5.4f)", func.getName(), argString(func), results));
    }

    private String argString(Indicator func) {
        return func.getArguments().toPrettyString();
    }
}
