package via.sep4.data.webapi.util;

public class Constants {
    public static final long ONE_HOUR = 3600000L;
    public static final String WEB_SOCKET_URL = "wss://iotnet.cibicom.dk/app?token=vnoUBQAAABFpb3RuZXQuY2liaWNvbS5ka4lPPjDJdv8czIiFOiS49tg=";
    public static final String RECEIVE_COMMAND = "rx";
    public static final String SEND_COMMAND = "tx";
    public static final String ACTIVATE_COMMAND = "activate";
    public static final String DEACTIVATE_COMMAND = "deactivate";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MESSAGE_REGEX = "^(.{4})(.{2})(.{3})(.{4})(.{1})";
    public static final int EUI_LENGTH = 16;
}
