package human.coejoder.mt4client;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an error from the MT4 terminal/server.
 */
public class MT4Exception extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(MT4Exception.class);

    private final ErrorCode errorCode;
    private final String errorCodeDescription;
    private final String message;

    /**
     * Constructor.
     *
     * @param errorCode            The error code.
     * @param errorCodeDescription The error code description.
     * @param message              Additional details about the error.
     */
    public MT4Exception(JsonNode errorCode, JsonNode errorCodeDescription, JsonNode message) {
        this.errorCode = (errorCode == null ? ErrorCode.UNKNOWN : ErrorCode.parse(errorCode.asInt(ErrorCode.UNKNOWN.id)));
        this.errorCodeDescription = (errorCodeDescription == null ? null : errorCodeDescription.asText(null));
        this.message = (message == null ? null : message.asText(null));
    }

    @Override
    public String toString() {
        if (errorCodeDescription != null && message != null) {
            return String.format("[MT4 Error %d: %s] %s%n  %s", errorCode.id, errorCode, errorCodeDescription, message);
        }
        else if (errorCodeDescription != null) {
            return String.format("[MT4 Error %d: %s] %s", errorCode.id, errorCode, errorCodeDescription);
        }
        else if (message != null) {
            return String.format("[MT4 Error %d: %s]%n  %s", errorCode.id, errorCode, message);
        }
        else {
            return String.format("[MT4 Error %d: %s]", errorCode.id, errorCode);
        }
    }

    /**
     * A MetaTrader 4 error code.
     *
     * @see <a href="https://docs.mql4.com/constants/errorswarnings/errorcodes">errorcodes</a>
     */
    @SuppressWarnings("unused")
    public enum ErrorCode {
        UNKNOWN(-1),
        ERR_NO_ERROR(0),
        ERR_NO_RESULT(1),
        ERR_COMMON_ERROR(2),
        ERR_INVALID_TRADE_PARAMETERS(3),
        ERR_SERVER_BUSY(4),
        ERR_OLD_VERSION(5),
        ERR_NO_CONNECTION(6),
        ERR_NOT_ENOUGH_RIGHTS(7),
        ERR_TOO_FREQUENT_REQUESTS(8),
        ERR_MALFUNCTIONAL_TRADE(9),
        ERR_ACCOUNT_DISABLED(64),
        ERR_INVALID_ACCOUNT(65),
        ERR_TRADE_TIMEOUT(128),
        ERR_INVALID_PRICE(129),
        ERR_INVALID_STOPS(130),
        ERR_INVALID_TRADE_VOLUME(131),
        ERR_MARKET_CLOSED(132),
        ERR_TRADE_DISABLED(133),
        ERR_NOT_ENOUGH_MONEY(134),
        ERR_PRICE_CHANGED(135),
        ERR_OFF_QUOTES(136),
        ERR_BROKER_BUSY(137),
        ERR_REQUOTE(138),
        ERR_ORDER_LOCKED(139),
        ERR_LONG_POSITIONS_ONLY_ALLOWED(140),
        ERR_TOO_MANY_REQUESTS(141),
        ERR_TRADE_MODIFY_DENIED(145),
        ERR_TRADE_CONTEXT_BUSY(146),
        ERR_TRADE_EXPIRATION_DENIED(147),
        ERR_TRADE_TOO_MANY_ORDERS(148),
        ERR_TRADE_HEDGE_PROHIBITED(149),
        ERR_TRADE_PROHIBITED_BY_FIFO(150),
        ERR_NO_MQLERROR(4000),
        ERR_WRONG_FUNCTION_POINTER(4001),
        ERR_ARRAY_INDEX_OUT_OF_RANGE(4002),
        ERR_NO_MEMORY_FOR_CALL_STACK(4003),
        ERR_RECURSIVE_STACK_OVERFLOW(4004),
        ERR_NOT_ENOUGH_STACK_FOR_PARAM(4005),
        ERR_NO_MEMORY_FOR_PARAM_STRING(4006),
        ERR_NO_MEMORY_FOR_TEMP_STRING(4007),
        ERR_NOT_INITIALIZED_STRING(4008),
        ERR_NOT_INITIALIZED_ARRAYSTRING(4009),
        ERR_NO_MEMORY_FOR_ARRAYSTRING(4010),
        ERR_TOO_LONG_STRING(4011),
        ERR_REMAINDER_FROM_ZERO_DIVIDE(4012),
        ERR_ZERO_DIVIDE(4013),
        ERR_UNKNOWN_COMMAND(4014),
        ERR_WRONG_JUMP(4015),
        ERR_NOT_INITIALIZED_ARRAY(4016),
        ERR_DLL_CALLS_NOT_ALLOWED(4017),
        ERR_CANNOT_LOAD_LIBRARY(4018),
        ERR_CANNOT_CALL_FUNCTION(4019),
        ERR_EXTERNAL_CALLS_NOT_ALLOWED(4020),
        ERR_NO_MEMORY_FOR_RETURNED_STR(4021),
        ERR_SYSTEM_BUSY(4022),
        ERR_DLLFUNC_CRITICALERROR(4023),
        ERR_INTERNAL_ERROR(4024),
        ERR_OUT_OF_MEMORY(4025),
        ERR_INVALID_POINTER(4026),
        ERR_FORMAT_TOO_MANY_FORMATTERS(4027),
        ERR_FORMAT_TOO_MANY_PARAMETERS(4028),
        ERR_ARRAY_INVALID(4029),
        ERR_CHART_NOREPLY(4030),
        ERR_INVALID_FUNCTION_PARAMSCNT(4050),
        ERR_INVALID_FUNCTION_PARAMVALUE(4051),
        ERR_STRING_FUNCTION_INTERNAL(4052),
        ERR_SOME_ARRAY_ERROR(4053),
        ERR_INCORRECT_SERIESARRAY_USING(4054),
        ERR_CUSTOM_INDICATOR_ERROR(4055),
        ERR_INCOMPATIBLE_ARRAYS(4056),
        ERR_GLOBAL_VARIABLES_PROCESSING(4057),
        ERR_GLOBAL_VARIABLE_NOT_FOUND(4058),
        ERR_FUNC_NOT_ALLOWED_IN_TESTING(4059),
        ERR_FUNCTION_NOT_CONFIRMED(4060),
        ERR_SEND_MAIL_ERROR(4061),
        ERR_STRING_PARAMETER_EXPECTED(4062),
        ERR_INTEGER_PARAMETER_EXPECTED(4063),
        ERR_DOUBLE_PARAMETER_EXPECTED(4064),
        ERR_ARRAY_AS_PARAMETER_EXPECTED(4065),
        ERR_HISTORY_WILL_UPDATED(4066),
        ERR_TRADE_ERROR(4067),
        ERR_RESOURCE_NOT_FOUND(4068),
        ERR_RESOURCE_NOT_SUPPORTED(4069),
        ERR_RESOURCE_DUPLICATED(4070),
        ERR_INDICATOR_CANNOT_INIT(4071),
        ERR_INDICATOR_CANNOT_LOAD(4072),
        ERR_NO_HISTORY_DATA(4073),
        ERR_NO_MEMORY_FOR_HISTORY(4074),
        ERR_NO_MEMORY_FOR_INDICATOR(4075),
        ERR_END_OF_FILE(4099),
        ERR_SOME_FILE_ERROR(4100),
        ERR_WRONG_FILE_NAME(4101),
        ERR_TOO_MANY_OPENED_FILES(4102),
        ERR_CANNOT_OPEN_FILE(4103),
        ERR_INCOMPATIBLE_FILEACCESS(4104),
        ERR_NO_ORDER_SELECTED(4105),
        ERR_UNKNOWN_SYMBOL(4106),
        ERR_INVALID_PRICE_PARAM(4107),
        ERR_INVALID_TICKET(4108),
        ERR_TRADE_NOT_ALLOWED(4109),
        ERR_LONGS_NOT_ALLOWED(4110),
        ERR_SHORTS_NOT_ALLOWED(4111),
        ERR_TRADE_EXPERT_DISABLED_BY_SERVER(4112),
        ERR_OBJECT_ALREADY_EXISTS(4200),
        ERR_UNKNOWN_OBJECT_PROPERTY(4201),
        ERR_OBJECT_DOES_NOT_EXIST(4202),
        ERR_UNKNOWN_OBJECT_TYPE(4203),
        ERR_NO_OBJECT_NAME(4204),
        ERR_OBJECT_COORDINATES_ERROR(4205),
        ERR_NO_SPECIFIED_SUBWINDOW(4206),
        ERR_SOME_OBJECT_ERROR(4207),
        ERR_CHART_PROP_INVALID(4210),
        ERR_CHART_NOT_FOUND(4211),
        ERR_CHARTWINDOW_NOT_FOUND(4212),
        ERR_CHARTINDICATOR_NOT_FOUND(4213),
        ERR_SYMBOL_SELECT(4220),
        ERR_NOTIFICATION_ERROR(4250),
        ERR_NOTIFICATION_PARAMETER(4251),
        ERR_NOTIFICATION_SETTINGS(4252),
        ERR_NOTIFICATION_TOO_FREQUENT(4253),
        ERR_FTP_NOSERVER(4260),
        ERR_FTP_NOLOGIN(4261),
        ERR_FTP_CONNECT_FAILED(4262),
        ERR_FTP_CLOSED(4263),
        ERR_FTP_CHANGEDIR(4264),
        ERR_FTP_FILE_ERROR(4265),
        ERR_FTP_ERROR(4266),
        ERR_FILE_TOO_MANY_OPENED(5001),
        ERR_FILE_WRONG_FILENAME(5002),
        ERR_FILE_TOO_LONG_FILENAME(5003),
        ERR_FILE_CANNOT_OPEN(5004),
        ERR_FILE_BUFFER_ALLOCATION_ERROR(5005),
        ERR_FILE_CANNOT_DELETE(5006),
        ERR_FILE_INVALID_HANDLE(5007),
        ERR_FILE_WRONG_HANDLE(5008),
        ERR_FILE_NOT_TOWRITE(5009),
        ERR_FILE_NOT_TOREAD(5010),
        ERR_FILE_NOT_BIN(5011),
        ERR_FILE_NOT_TXT(5012),
        ERR_FILE_NOT_TXTORCSV(5013),
        ERR_FILE_NOT_CSV(5014),
        ERR_FILE_READ_ERROR(5015),
        ERR_FILE_WRITE_ERROR(5016),
        ERR_FILE_BIN_STRINGSIZE(5017),
        ERR_FILE_INCOMPATIBLE(5018),
        ERR_FILE_IS_DIRECTORY(5019),
        ERR_FILE_NOT_EXIST(5020),
        ERR_FILE_CANNOT_REWRITE(5021),
        ERR_FILE_WRONG_DIRECTORYNAME(5022),
        ERR_FILE_DIRECTORY_NOT_EXIST(5023),
        ERR_FILE_NOT_DIRECTORY(5024),
        ERR_FILE_CANNOT_DELETE_DIRECTORY(5025),
        ERR_FILE_CANNOT_CLEAN_DIRECTORY(5026),
        ERR_FILE_ARRAYRESIZE_ERROR(5027),
        ERR_FILE_STRINGRESIZE_ERROR(5028),
        ERR_FILE_STRUCT_WITH_OBJECTS(5029),
        ERR_WEBREQUEST_INVALID_ADDRESS(5200),
        ERR_WEBREQUEST_CONNECT_FAILED(5201),
        ERR_WEBREQUEST_TIMEOUT(5202),
        ERR_WEBREQUEST_REQUEST_FAILED(5203),
        ERR_USER_ERROR_FIRST(65536);

        private static final ErrorCode[] COPY_OF_VALUES = values();

        public final int id;

        ErrorCode(int id) {
            this.id = id;
        }

        public static ErrorCode parse(int intCode) {
            for (ErrorCode code : COPY_OF_VALUES) {
                if (intCode == code.id) {
                    return code;
                }
            }
            LOG.error("Unknown error code: " + intCode);
            return ErrorCode.UNKNOWN;
        }
    }
}