package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected static final Logger LOG = LoggerFactory.getLogger(TestAccount.class);
    private static final String ADDRESS = "tcp://127.0.0.1:28282";

    protected MT4Client mt4;

    @BeforeClass
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        mt4 = new MT4Client(ADDRESS);
    }

    @AfterClass
    public void afterClass() {
        mt4.shutdown();
    }
}
