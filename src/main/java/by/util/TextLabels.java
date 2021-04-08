package by.util;

import java.util.Properties;

public class TextLabels {

    //properties
    public static Properties property = new Properties();

    public static final String AGE = "age";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String MILEAGE = "mileage";
    public static final String ENGINE_SIZE_PARAM = "engineSize";
    public static final String ENGINE_POWER_PARAM = "enginePower";

    //common
    public static final String ID = "id";

    //properties
    public static final String PROPERTIES_PATH = "C:\\Users\\user\\IdeaProjects\\SimpleWeb\\src\\main\\resources\\properties.properties";
    public static final String PROPERTIES_MAX_FILE_SIZE = "maxFileSize";
    public static final String PROPERTIES_MAX_MEMORY_SIZE = "maxMemSize";
    public static final String PROPERTIES_BASE_PATH = "basePath";

    //symbols
    public static final String DOT = ".";
    public static final String ANY_NOT_NUMERAL_SYMBOL = "\\D";
    public static final String EMPTY = "";
    public static final String EQUALLY = "=";
    public static final String SPLITTER = "&";

    //file
    public static final String JSON_FILE = "application/json";
    public static final String IMAGE_FILE = "image/gif";
    public static final String DEF_NAME = "def";
    public static final String UTF8 = "UTF-8";

    //servlet
    public static final String SIZE_PARAM = "size";
    public static final String PAGE_PARAM = "page";

    //logger messages
    public static final String LOG_FILTER_INIT = "LogFilter initialized";
    public static final String LOG_FILTER_DESTROY = "LogFilter destroyed";
    public static final String AUTH_FILTER_INIT = "AuthFilter initialized";
    public static final String AUTH_FILTER_DESTROY = "AuthFilter destroyed";
    public static final String EXCEPTION_MESSAGE = "Exception - ";
    public static final String HTTP_METHOD_MESSAGE = " HTTP Method - ";

    //exception
    public static final String CONNECTION_WITH_DB_LOST_EXCEPTION_LABEL = "Connection with db lost";
    public static final String NULL_SQL_QUERY_EXCEPTION_LABEL = "Sql query for this parameter(s) returns null";
    public static final String JSON_PARSER_EXCEPTION_LABEL = "Object can't parse to JSON format";
    public static final String RESPONSE_EXCEPTION_LABEL = "An input or output exception occurred in response";
    public static final String REQUEST_EXCEPTION_LABEL = "An input or output exception occurred in request";
    public static final String FILE_JSON_EXCEPTION_LABEL = "Cannot import this file to json";
    public static final String REQUEST_PARSER_EXCEPTION_LABEL = "Incorrect parameter(s) in request";
    public static final String AUTH_EXCEPTION_LABEL = "Cannot auth(some user authed now)";
    public static final String REQUEST_HEADER_EXCEPTION_LABEL = "Wrong header(s) in request";

    //frame special keys
    public static final String READ = "r";
}