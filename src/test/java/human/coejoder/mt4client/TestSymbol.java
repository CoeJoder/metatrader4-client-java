package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

public class TestSymbol extends TestBase {

    @Test
    public void testGetSymbolNames() throws JsonProcessingException, MT4Exception {
        LOG.trace(String.join(", ", mt4.getSymbolNames()));
    }
}
