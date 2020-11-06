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

    @Test
    public void testMarketBuy() throws JsonProcessingException, MT4Exception {
        // create a market order using relative stops
        OrderType orderType = OrderType.OP_BUY;
        double bid = symbol.getTick().bid;
        int points = 50;
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

    @Test
    public void testMarketSell() throws JsonProcessingException, MT4Exception {
        // create a market order using absolute stops
        OrderType orderType = OrderType.OP_SELL;
        double bid = symbol.getTick().bid;
        int points = 100;
        double sl = bid + points * symbol.getPoint();
        double tp = bid - points * symbol.getPoint();
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol.getName())
                .setOrderType(orderType)
                .setLots(lots)
                .setSl(sl)
                .setTp(tp)
                .build());

        LOG.trace(String.format("New market sell: %s", order));
        Assert.assertEquals(order.getOrderType(), orderType, "Wrong order type.");
        Assert.assertTrue(order.getSl() > bid, "Expected stop-loss to be greater than current bid.");
        Assert.assertTrue(order.getTp() < bid, "Expected take-profit to be less than current bid.");
    }
}
