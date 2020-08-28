package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAccount {

    private static final String ADDRESS = "tcp://win10:28282";
    private MT4Client mt4;

    @BeforeClass
    public void beforeClass() {
        mt4 = new MT4Client(ADDRESS);
    }

    @AfterClass
    public void afterClass() {
        mt4.shutdown();
    }

    @Test
    public void testAccount() throws JsonProcessingException, MT4Exception {
        Account account = mt4.getAccount();
        System.err.println(account);
    }
}
