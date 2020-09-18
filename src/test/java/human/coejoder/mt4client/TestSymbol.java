package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSymbol extends TestBase {

    private Symbol symbol;

    @BeforeClass
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        super.beforeClass();
        String name = mt4.getSymbolNames().iterator().next();
        symbol = mt4.getSymbol(name);
        LOG.trace("Symbol: " + symbol);
    }

    @Test
    public void testGetSymbolNames() throws JsonProcessingException, MT4Exception {
        LOG.trace("Symbol names: " + String.join(", ", mt4.getSymbolNames()));
    }

    @Test
    public void testGetSymbolTick() throws JsonProcessingException, MT4Exception {
        LOG.trace("Symbol tick: " + symbol.getTick());
    }
}
