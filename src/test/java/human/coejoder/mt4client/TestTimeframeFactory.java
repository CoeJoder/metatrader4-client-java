package human.coejoder.mt4client;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link TimeframeFactory}.
 */
public class TestTimeframeFactory {

    @DataProvider
    public Object[][] validTimeframes() {
        return new Object[][] {
                new Object[] {"15m", StandardTimeframe.PERIOD_M15},
                new Object[] {"1h", StandardTimeframe.PERIOD_H1},
                new Object[] {"1d", StandardTimeframe.PERIOD_D1},
                new Object[] {"1w", StandardTimeframe.PERIOD_W1},
                new Object[] {"1mn", StandardTimeframe.PERIOD_MN1},
                new Object[] {"0", StandardTimeframe.PERIOD_CURRENT},
                new Object[] {"2m", NonStandardTimeframe.PERIOD_M2},
                new Object[] {"2h", NonStandardTimeframe.PERIOD_H2}
        };
    }

    @DataProvider
    public Object[][] invalidTimeframes() {
        return new Object[][] {
                new Object[] {"10a"},
                new Object[] {"-15m"},
                new Object[] {"M15"}
        };
    }

    @Test(dataProvider = "validTimeframes")
    public void testValidTimeframes(String timeframe, Timeframe expected) {
        TimeframeFactory.INSTANCE.build(timeframe).ifPresentOrElse(
                t -> Assert.assertEquals(t, expected),
                () -> Assert.fail("Failed to parse valid timeframe.")
        );
    }

    @Test(dataProvider = "invalidTimeframes")
    public void testInvalidTimeframe(String timeframe) {
        TimeframeFactory.INSTANCE.build(timeframe).ifPresent(
                t -> Assert.fail("Non-empty value for invalid timeframe.")
        );
    }
}
