package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An order modification request to be sent to the server.
 *
 * @see <a href="https://book.mql4.com/trading/ordermodify">https://book.mql4.com/trading/ordermodify</a>
 * @see <a href="https://book.mql4.com/appendix/limits">https://book.mql4.com/appendix/limits</a>
 */
public class ModifyOrder {

    public static class Builder implements OrderStep {
        private int ticket;
        private Double price;
        private Double sl;
        private Double tp;
        private Integer slPoints;
        private Integer tpPoints;

        private Builder() {}

        /**
         * Construct a modify-order builder.
         *
         * @return The modify-order builder instance.
         */
        public static OrderStep newInstance() {
            return new Builder();
        }

        /**
         * Build the modify-order instance.
         *
         * @return The modify-order request.
         */
        public ModifyOrder build() {
            return new ModifyOrder(this);
        }

        /**
         * @param ticket The order ticket number.
         * @return This Builder.
         */
        @Override
        public Builder setOrder(int ticket) {
            this.ticket = ticket;
            return this;
        }

        /**
         * @param order The order to modify.
         * @return This Builder.
         */
        @Override
        public Builder setOrder(Order order) {
            this.ticket = order.getTicket();
            return this;
        }

        /**
         * @param price The desired open price; applies to pending orders only.  Optional.
         * @return This Builder.
         */
        public Builder setPrice(Double price) {
            this.price = price;
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
    }

    public interface OrderStep {
        Builder setOrder(int ticket);
        Builder setOrder(Order order);
    }

    public final int ticket;
    public final Double price;
    public final Double sl;
    public final Double tp;
    @JsonProperty("sl_points")
    public final int slPoints;
    @JsonProperty("tp_points")
    public final int tpPoints;

    private ModifyOrder(Builder builder) {
        this.ticket = builder.ticket;
        this.price = builder.price;
        this.sl = builder.sl;
        this.tp = builder.tp;
        this.slPoints = builder.slPoints;
        this.tpPoints = builder.tpPoints;
    }
}
