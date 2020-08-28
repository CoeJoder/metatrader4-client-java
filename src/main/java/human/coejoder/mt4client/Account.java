package human.coejoder.mt4client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A MetaTrader 4 account.
 */
public class Account {

    private static final String PROPERTY_ID = "property_id";
    private static final String LOGIN = "login";
    private static final String TRADE_MODE = "trade_mode";
    private static final String NAME = "name";
    private static final String SERVER = "server";
    private static final String CURRENCY = "currency";
    private static final String COMPANY = "company";

    private final MT4Client mt4;
    public final int login;
    public final AccountTradeMode tradeMode;
    public final String name;
    public final String server;
    public final String currency;
    public final String company;

    /**
     * Package-private constructor.
     *
     * @param mt4      The {@link MT4Client} object.
     * @param response The basic account info as returned by the server.
     */
    Account(MT4Client mt4, JsonNode response) {
        this.mt4 = mt4;
        this.login = response.get(LOGIN).asInt();
        this.tradeMode = AccountTradeMode.fromInt(response.get(TRADE_MODE).asInt());
        this.name = response.get(NAME).asText();
        this.server = response.get(SERVER).asText();
        this.currency = response.get(CURRENCY).asText();
        this.company = response.get(COMPANY).asText();
    }

    private int getAccountInfoInteger(AccountInfoInteger prop) throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_ACCOUNT_INFO_INTEGER.build()
                .put(PROPERTY_ID, prop.id))
                .asInt();
    }

    private double getAccounttInfoDouble(AccountInfoDouble prop) throws JsonProcessingException, MT4Exception {
        return mt4.getResponse(Request.GET_ACCOUNT_INFO_DOUBLE.build()
                .put(PROPERTY_ID, prop.id))
                .asDouble();
    }
}
