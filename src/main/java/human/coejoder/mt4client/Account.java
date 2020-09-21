package human.coejoder.mt4client;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * A MetaTrader 4 account.
 */
public class Account {

    private static final String PROPERTY_ID = "property_id";
    private static final String TRADE_MODE = "trade_mode";

    private final MT4Client mt4;
    private final long login;
    private final AccountTradeMode tradeMode;
    private final String name;
    private final String server;
    private final String currency;
    private final String company;

    /**
     * Package-private constructor.
     *
     * @param mt4       The {@link MT4Client} object.
     * @param login     Account number.
     * @param tradeMode Account trade mode.
     * @param name      Client name.
     * @param server    Trade server name.
     * @param currency  Account currency.
     * @param company   Name of a company which serves the account.
     */
    @JsonCreator
    Account(@JacksonInject MT4Client mt4,
            long login,
            @JsonProperty(TRADE_MODE) int tradeMode,
            String name,
            String server,
            String currency,
            String company) {
        this.mt4 = mt4;
        this.login = login;
        this.tradeMode = AccountTradeMode.fromId(tradeMode).orElseThrow();
        this.name = name;
        this.server = server;
        this.currency = currency;
        this.company = company;
    }

    /**
     * Account number.
     *
     * @return <code>AccountInfoInteger(ACCOUNT_LOGIN)</code>
     */
    public long getLogin() {
        return login;
    }

    /**
     * Account trade mode.
     *
     * @return <code>AccountInfoInteger(ACCOUNT_TRADE_MODE)</code>
     */
    public AccountTradeMode getTradeMode() {
        return tradeMode;
    }

    /**
     * Client name.
     *
     * @return <code>AccountInfoString(ACCOUNT_NAME)</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Trade server name.
     *
     * @return <code>AccountInfoString(ACCOUNT_SERVER)</code>
     */
    public String getServer() {
        return server;
    }

    /**
     * Account currency.
     *
     * @return <code>AccountInfoString(ACCOUNT_CURRENCY)</code>
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Name of a company which serves the account.
     *
     * @return <code>AccountInfoString(ACCOUNT_COMPANY)</code>
     */
    public String getCompany() {
        return company;
    }

    /**
     * Account leverage.
     *
     * @return The account leverage.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public long getLeverage() throws JsonProcessingException, MT4Exception {
        return getAccountInfoInteger(AccountInfoInteger.ACCOUNT_LEVERAGE);
    }

    /**
     * Maximum allowed number of open positions and active pending orders (in total), 0 = unlimited.
     *
     * @return The limit order.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public int getLimitOrders() throws JsonProcessingException, MT4Exception {
        return (int)getAccountInfoInteger(AccountInfoInteger.ACCOUNT_LIMIT_ORDERS);
    }

    /**
     * Mode for setting the minimal allowed margin.
     *
     * @return The mode setting.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public AccountStopoutMode getMarginStopOutMode() throws JsonProcessingException, MT4Exception {
        return AccountStopoutMode.fromId((int)getAccountInfoInteger(AccountInfoInteger.ACCOUNT_MARGIN_SO_MODE))
                .orElseThrow();
    }

    /**
     * Allowed trade for the current account.
     *
     * @return Whether trading is allowed.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public boolean isTradeAllowed() throws JsonProcessingException, MT4Exception {
        return (int)getAccountInfoInteger(AccountInfoInteger.ACCOUNT_TRADE_ALLOWED) == 1;
    }

    /**
     * Allowed trade for an Expert Advisor.
     *
     * @return Whether trading via an Expert Advisor is allowed.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public int isTradeForExpertAdvisorAllowed() throws JsonProcessingException, MT4Exception {
        return (int)getAccountInfoInteger(AccountInfoInteger.ACCOUNT_TRADE_EXPERT);
    }

    /**
     * Account balance in the deposit currency.
     *
     * @return The account balance.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getBalance() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_BALANCE);
    }

    /**
     * Account credit in the deposit currency.
     *
     * @return The account credit.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getCredit() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_CREDIT);
    }

    /**
     * Current profit of an account in the deposit currency.
     *
     * @return The current profit.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getProfit() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_PROFIT);
    }

    /**
     * Account equity in the deposit currency.
     *
     * @return The account equity.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getEquity() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_EQUITY);
    }

    /**
     * Account margin used in the deposit currency.
     *
     * @return The account margin.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getMargin() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_MARGIN);
    }

    /**
     * Free margin of an account in the deposit currency.
     *
     * @return The account's free margin.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getMarginFree() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_MARGIN_FREE);
    }

    /**
     * Account margin level in percents.
     *
     * @return The account's margin level.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getMarginLevel() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_MARGIN_LEVEL);
    }

    /**
     * Margin call level. Depending on {@link #getMarginStopOutMode() margin stop-out mode}, this is expressed in
     * percents or in the deposit currency.
     *
     * @return The margin call level.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getMarginCallLevel() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_MARGIN_SO_CALL);
    }

    /**
     * Margin stop-out level. Depending on the {@link #getMarginStopOutMode() margin stop-out mode}, this is expressed
     * in percents or in the deposit currency.
     *
     * @return The margin stop-out level.
     * @throws JsonProcessingException If JSON response fails to parse.
     * @throws MT4Exception            If server had an error.
     */
    public double getMarginStopOutLevel() throws JsonProcessingException, MT4Exception {
        return getAccountInfoDouble(AccountInfoDouble.ACCOUNT_MARGIN_SO_SO);
    }

    private long getAccountInfoInteger(AccountInfoInteger prop) throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_ACCOUNT_INFO_INTEGER.build()
                .put(PROPERTY_ID, prop.id), long.class);
    }

    private double getAccountInfoDouble(AccountInfoDouble prop) throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_ACCOUNT_INFO_DOUBLE.build()
                .put(PROPERTY_ID, prop.id), double.class);
    }

    @Override
    public String toString() {
        return "Account{" +
                "login=" + login +
                ", tradeMode=" + tradeMode +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", currency='" + currency + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
