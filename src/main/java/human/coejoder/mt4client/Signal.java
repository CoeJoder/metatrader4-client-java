package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A trading signal in MetaTrader 4.
 *
 * @see <a href="https://docs.mql4.com/signals">signals</a>
 * @see <a href="https://docs.mql4.com/constants/tradingconstants/signalproperties">signalproperties</a>
 */
public class Signal {

    private static final String AUTHOR_LOGIN = "author_login";
    private static final String BROKER_SERVER = "broker_server";
    private static final String DATE_PUBLISHED = "date_published";
    private static final String DATE_STARTED = "date_started";
    private static final String TRADE_MODE = "trade_mode";
    private static final String MAX_DRAWDOWN = "max_drawdown";

    private final String authorLogin;
    private final String broker;
    private final String brokerServer;
    private final String name;
    private final String currency;
    private final int datePublished;
    private final int dateStarted;
    private final int id;
    private final int leverage;
    private final int pips;
    private final int rating;
    private final int subscribers;
    private final int trades;
    private final int tradeMode;
    private final double balance;
    private final double equity;
    private final double gain;
    private final double maxDrawdown;
    private final double price;
    private final double roi;

    /**
     * Package-private constructor.
     *
     * @param authorLogin   Author login.
     * @param broker        Broker name (company).
     * @param brokerServer  Broker server.
     * @param name          Signal name.
     * @param currency      Signal base currency.
     * @param datePublished Publication date (date when it become available for subscription).
     * @param dateStarted   Monitoring start date.
     * @param id            Signal ID.
     * @param leverage      Account leverage.
     * @param pips          Profit in pips.
     * @param rating        Position iin rating.
     * @param subscribers   Number of subscribers.
     * @param trades        Number of trades.
     * @param tradeMode     Account type.
     * @param balance       Account balance.
     * @param equity        Account equity.
     * @param gain          Account gain.
     * @param maxDrawdown   Account maximum drawdown.
     * @param price         Signal subscription price.
     * @param roi           Return on investment (%).
     */
    @JsonCreator
    Signal(@JsonProperty(AUTHOR_LOGIN) String authorLogin,
           String broker,
           @JsonProperty(BROKER_SERVER) String brokerServer,
           String name,
           String currency,
           @JsonProperty(DATE_PUBLISHED) int datePublished,
           @JsonProperty(DATE_STARTED) int dateStarted,
           int id,
           int leverage,
           int pips,
           int rating,
           int subscribers,
           int trades,
           @JsonProperty(TRADE_MODE) int tradeMode,
           double balance,
           double equity,
           double gain,
           @JsonProperty(MAX_DRAWDOWN) double maxDrawdown,
           double price,
           double roi) {
        this.authorLogin = authorLogin;
        this.broker = broker;
        this.brokerServer = brokerServer;
        this.name = name;
        this.currency = currency;
        this.datePublished = datePublished;
        this.dateStarted = dateStarted;
        this.id = id;
        this.leverage = leverage;
        this.pips = pips;
        this.rating = rating;
        this.subscribers = subscribers;
        this.trades = trades;
        this.tradeMode = tradeMode;
        this.balance = balance;
        this.equity = equity;
        this.gain = gain;
        this.maxDrawdown = maxDrawdown;
        this.price = price;
        this.roi = roi;
    }

    /**
     * Author login.
     *
     * @return <code>SignalBaseGetString(SIGNAL_BASE_AUTHOR_LOGIN)</code>
     */
    public String getAuthorLogin() {
        return authorLogin;
    }

    /**
     * Broker name (company).
     *
     * @return <code>SignalBaseGetString(SIGNAL_BASE_BROKER)</code>
     */
    public String getBroker() {
        return broker;
    }

    /**
     * Broker server.
     *
     * @return <code>SignalBaseGetString(SIGNAL_BASE_BROKER_SERVER)</code>
     */
    public String getBrokerServer() {
        return brokerServer;
    }

    /**
     * Signal name.
     *
     * @return <code>SignalBaseGetString(SIGNAL_BASE_NAME)</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Signal base currency.
     *
     * @return <code>SignalBaseGetString(SIGNAL_BASE_CURRENCY)</code>
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Publication date (date when it become available for subscription).
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_DATE_PUBLISHED)</code>
     */
    public int getDatePublished() {
        return datePublished;
    }

    /**
     * Monitoring start date.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_DATE_STARTED)</code>
     */
    public int getDateStarted() {
        return dateStarted;
    }

    /**
     * Signal ID.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_ID)</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Account leverage.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_LEVERAGE)</code>
     */
    public int getLeverage() {
        return leverage;
    }

    /**
     * Profit in pips.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_PIPS)</code>
     */
    public int getPips() {
        return pips;
    }

    /**
     * Position iin rating.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_RATING)</code>
     */
    public int getRating() {
        return rating;
    }

    /**
     * Number of subscribers.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_SUBSCRIBERS)</code>
     */
    public int getSubscribers() {
        return subscribers;
    }

    /**
     * Number of trades.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_TRADES)</code>
     */
    public int getTrades() {
        return trades;
    }

    /**
     * Account type.
     *
     * @return <code>SignalBaseGetInteger(SIGNAL_BASE_TRADE_MODE)</code>
     */
    public int getTradeMode() {
        return tradeMode;
    }

    /**
     * Account balance.
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_BALANCE)</code>
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Account equity.
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_EQUITY)</code>
     */
    public double getEquity() {
        return equity;
    }

    /**
     * Account gain.
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_GAIN)</code>
     */
    public double getGain() {
        return gain;
    }

    /**
     * Account maximum drawdown.
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_MAX_DRAWDOWN)</code>
     */
    public double getMaxDrawdown() {
        return maxDrawdown;
    }

    /**
     * Signal subscription price.
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_PRICE)</code>
     */
    public double getPrice() {
        return price;
    }

    /**
     * Return on investment (%).
     *
     * @return <code>SignalBaseGetDouble(SIGNAL_BASE_ROI)</code>
     */
    public double getRoi() {
        return roi;
    }
}
