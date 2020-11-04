package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Joiner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Integration tests for {@link Signal trade signals}.
 */
public class TestSignal extends TestBase {

    private List<String> signalNames;

    @BeforeClass
    @Override
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        super.beforeClass();
        signalNames = mt4.getSignalNames();
    }

    @DataProvider()
    private Object[][] signalNameProvider() {
        Object[][] sigNames = new Object[signalNames.size()][];
        for (int i = 0; i < signalNames.size(); i++) {
            sigNames[i] = new Object[] {sigNames[i]};
        }
        return sigNames;
    }

    @Test(dataProvider = "signalNameProvider")
    public void testGetSignal(String signalName) throws JsonProcessingException, MT4Exception {
        LOG.trace(String.format("%s = %s", signalName, mt4.getSignal(signalName)));
    }

    @Test
    public void testGetSignals() throws JsonProcessingException, MT4Exception {
        Map<String, Signal> signals = mt4.getSignals(signalNames.toArray(String[]::new));
        LOG.trace(Joiner.on("\n").withKeyValueSeparator(" = ").join(signals));
    }
}
