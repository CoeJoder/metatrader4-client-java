package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestOrder extends TestBase {

    private static final double MIN_LOTS_BOUND = 1D;
    private static final boolean CLOSE_IF_OPENED = true;

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
                .setSymbol(symbol)
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
                .setSymbol(symbol)
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

    @Test
    public void testLimitBuy() throws JsonProcessingException, MT4Exception {
        // create a pending buy order with relative sl/tp
        OrderType orderType = OrderType.OP_BUYLIMIT;
        double optimisticBuyPrice = symbol.getTick().ask / 2;
        int slippage = 1;
        int slPoints = 100;
        int tpPoints = 100;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol)
                .setOrderType(orderType)
                .setLots(lots)
                .setPrice(optimisticBuyPrice)
                .setSlippage(slippage)
                .setSlPoints(slPoints)
                .setTpPoints(tpPoints)
                .build());

        LOG.trace(String.format("New pending buy order: %s", order));
        Assert.assertEquals(order.getOrderType(), orderType, "Wrong order type.");
    }

    @Test
    public void testLimitSell() throws JsonProcessingException, MT4Exception {
        // create a pending sell order with no sl/tp
        OrderType orderType = OrderType.OP_SELLLIMIT;
        double optimisticSellPrice = symbol.getTick().bid * 2;
        int slippage = 1;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol)
                .setOrderType(orderType)
                .setLots(lots)
                .setPrice(optimisticSellPrice)
                .setSlippage(slippage)
                .build());

        LOG.trace(String.format("New pending sell order: %s", order));
        Assert.assertEquals(order.getOrderType(), orderType, "Wrong order type.");
    }

    @Test
    public void testModifyOpenOrder() throws JsonProcessingException, MT4Exception {
        // create market order
        OrderType orderType = OrderType.OP_BUY;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol)
                .setOrderType(orderType)
                .setLots(lots)
                .build());

        // add sl/tp stops
        double bid = symbol.getTick().bid;
        int points = 200;
        double sl = bid - points * symbol.getPoint();
        double tp = bid + points * symbol.getPoint();

        // modify order
        order = mt4.orderModify(ModifyOrder.Builder.newInstance()
                .setOrder(order)
                .setSl(sl)
                .setTp(tp)
                .build());
        Assert.assertTrue(order.getSl() < bid, "Unexpected stop-loss.");
        Assert.assertTrue(order.getTp() > bid, "Unexpected take-profit.");
    }

    @Test
    public void testCloseOpenOrder() throws JsonProcessingException, MT4Exception {
        // create market order
        OrderType orderType = OrderType.OP_BUY;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol)
                .setOrderType(orderType)
                .setLots(lots)
                .build());

        // assert that the order was created and is open
        Assert.assertEquals(order.getOrderType(), orderType);

        // close the order
        mt4.orderClose(order);
    }

    @Test
    public void testDeletePendingOrder() throws JsonProcessingException, MT4Exception {
        // create pending order
        OrderType orderType = OrderType.OP_BUYLIMIT;
        double optimisticBuyPrice = symbol.getTick().ask / 2;
        Order order = mt4.orderSend(NewOrder.Builder.newInstance()
                .setSymbol(symbol)
                .setOrderType(orderType)
                .setLots(lots)
                .setPrice(optimisticBuyPrice)
                .build());

        // assert that the order was created and is pending
        Assert.assertEquals(order.getOrderType(), orderType);

        // delete the order
        int ticket = order.getTicket();
        mt4.orderDelete(order);
        order = mt4.getOrder(ticket);
        Assert.assertNull(order);
    }

    @Test
    public void testOrders() throws JsonProcessingException, MT4Exception {
        mt4.getOrders();
    }

    @Test
    public void testOrdersHistorical() throws JsonProcessingException, MT4Exception {
        mt4.getOrdersHistorical();
    }

    @Test
    public void testCloseAllOrders() throws JsonProcessingException, MT4Exception {
        for (Order order : mt4.getOrders()) {
            mt4.orderDelete(order, CLOSE_IF_OPENED);
        }

        // assert that all orders were deleted
        Assert.assertEquals(mt4.getOrders().size(), 0);
    }
}
