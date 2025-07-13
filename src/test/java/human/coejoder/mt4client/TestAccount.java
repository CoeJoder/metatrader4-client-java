package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Integration tests for {@link Account}.
 */
public class TestAccount extends TestBase {

    private static final String ADDRESS = "tcp://localhost:28282";
    private Account account;

    @BeforeClass
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        super.beforeClass();
        account = mt4.getAccount();
    }

    @Test
    public void testAccountLeverage() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account leverage: " + account.getLeverage());
    }

    @Test
    public void testGetLimitOrders() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account limit orders: " + account.getLimitOrders());
    }

    @Test
    public void testGetMarginStopOutMode() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account margin stop-out mode: " + account.getMarginStopOutMode());
    }

    @Test
    public void testIsTradeAllowed() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account is trade allowed: " + account.isTradeAllowed());
    }

    @Test
    public void testIsTradeForExpertAdvisorAllowed() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account is trade for EA allowed: " + account.isTradeForExpertAdvisorAllowed());
    }

    @Test
    public void testGetBalance() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account balance: " + account.getBalance());
    }

    @Test
    public void testGetCredit() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account credit: " + account.getCredit());
    }

    @Test
    public void testGetProfit() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account profit: " + account.getProfit());
    }

    @Test
    public void testGetEquity() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account equity: " + account.getEquity());
    }

    @Test
    public void testGetMargin() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account margin: " + account.getMargin());
    }

    @Test
    public void testGetMarginFree() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account free margin: " + account.getMarginFree());
    }

    @Test
    public void testGetMarginLevel() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account margin level: " + account.getMarginLevel());
    }

    @Test
    public void testGetMarginCallLevel() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account margin call level: " + account.getMarginCallLevel());
    }

    @Test
    public void testGetMarginStopOutLevel() throws JsonProcessingException, MT4Exception {
        LOG.trace("Account margin stop-out level: " + account.getMarginStopOutLevel());
    }
}
