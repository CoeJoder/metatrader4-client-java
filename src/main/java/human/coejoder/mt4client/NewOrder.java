package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A new order request to be sent to the server.
 *
 * @see <a href="https://docs.mql4.com/trading/ordersend">https://docs.mql4.com/trading/ordersend</a>
 * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
 */
public class NewOrder {

    /**
     * Step builder for new order requests.
     */
    public static class Builder implements SymbolStep, OrderTypeStep, LotsStep {

        private String symbol;
        private OrderType orderType;
        private double lots;
        private Double price;
        private Integer slippage;
        private Double sl;
        private Double tp;
        private Integer slPoints;
        private Integer tpPoints;
        private String comment;

        private Builder() {}

        /**
         * Construct a new market order builder.
         *
         * @return The market order builder instance.
         */
        public static SymbolStep newInstance() {
            return new Builder();
        }

        /**
         * Build the new order request.
         *
         * @return The new order request.
         */
        public NewOrder build() {
            return new NewOrder(this);
        }

        /**
         * @param symbol The market symbol.
         * @return This Builder.
         */
        @Override
        public OrderTypeStep setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * @param orderType The order type.
         * @return This Builder.
         */
        @Override
        public LotsStep setOrderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        /**
         * @param lots The number of lots to trade.
         * @return This Builder.
         */
        @Override
        public Builder setLots(double lots) {
            this.lots = lots;
            return this;
        }

        /**
         * @param price The desired open price.  Optional for market orders.
         * @return This Builder.
         */
        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        /**
         * @param slippage The maximum price slippage, in points.  Omit to use a permissive default (2.0 * spread).
         * @return This Builder.
         */
        public Builder setSlippage(Integer slippage) {
            this.slippage = slippage;
            return this;
        }

        /**
         * @param sl The absolute stop-loss to use.  Optional.
         * @return This Builder.
         */
        public Builder setSl(Double sl) {
            this.sl = sl;
            return this;
        }

        /**
         * @param tp The absolute take-profit to use.  Optional.
         * @return This Builder.
         */
        public Builder setTp(Double tp) {
            this.tp = tp;
            return this;
        }

        /**
         * @param slPoints The relative stop-loss to use, in points.  Optional.
         * @return This Builder.
         */
        public Builder setSlPoints(Integer slPoints) {
            this.slPoints = slPoints;
            return this;
        }

        /**
         * @param tpPoints The relative take-profit to use, in points.  Optional.
         * @return This Builder.
         */
        public Builder setTpPoints(Integer tpPoints) {
            this.tpPoints = tpPoints;
            return this;
        }

        /**
         * @param comment The order comment text.  Last part of the comment may be changed by server.  Optional.
         * @return This Builder.
         */
        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }
    }

    public interface SymbolStep {
        OrderTypeStep setSymbol(String symbol);
    }

    public interface OrderTypeStep {
        LotsStep setOrderType(OrderType orderType);
    }

    public interface LotsStep {
        Builder setLots(double lots);
    }

    public final String symbol;
    @JsonProperty("order_type")
    public final OrderType orderType;
    public final Double lots;
    public final Double price;
    public final Integer slippage;
    public final Double sl;
    public final Double tp;
    @JsonProperty("sl_points")
    public final Integer slPoints;
    @JsonProperty("tp_points")
    public final Integer tpPoints;
    public final String comment;

    private NewOrder(Builder builder) {
        // do some parameter validation
        if (!builder.orderType.isBuy() && !builder.orderType.isSell()) {
            throw new IllegalArgumentException(String.format("Invalid order type: %s", builder.orderType));
        }
        if (builder.orderType.isPending() && builder.price == null) {
            throw new IllegalArgumentException("Pending orders must specify a price");
        }

        this.symbol = builder.symbol;
        this.orderType = builder.orderType;
        this.lots = builder.lots;
        this.price = builder.price;
        this.slippage = builder.slippage;
        this.sl = builder.sl;
        this.tp = builder.tp;
        this.slPoints = builder.slPoints;
        this.tpPoints = builder.tpPoints;
        this.comment = (builder.comment == null ? "" : builder.comment);
    }
}
