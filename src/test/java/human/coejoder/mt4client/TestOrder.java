package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestOrder extends TestBase {

    private static final double MIN_LOTS_BOUND = 1D;

    private Symbol symbol;
    private double lots;

    @BeforeClass
    @Override
    public void beforeClass() throws JsonProcessingException, MT4Exception {
        super.beforeClass();
        symbol = mt4.getSymbol(mt4.getSymbolNames().iterator().next());
        lots = Math.max(MIN_LOTS_BOUND, symbol.getVolumeMin());
    }

    @Test(enabled = false)
    public void testMarketBuy() throws JsonProcessingException, MT4Exception {
        double bid = symbol.getTick().bid;
        int points = 50;
        OrderType orderType = OrderType.OP_BUY;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol.getName())
                .setOrderType(orderType)
                .setLots(lots)
                .setSlPoints(points)
                .setTpPoints(points)
                .build());

        LOG.trace(String.format("New market buy: %s", order));
        Assert.assertEquals(order.getOrderType(), orderType, "Wrong order type.");
        Assert.assertTrue(order.getSl() < bid, "Expected stop-loss to be less than current bid.");
        Assert.assertTrue(order.getTp() > bid, "Expected take-profit to be greater than current bid.");
    }
}
