package by.util;

import java.util.Properties;

public class TextLabels {

    //spring consts
    public static final String INTERCEPTOR_PATH_PATTERN = "/**";
    public static final String MAIN_SERVLET_NAME = "mvc";
    public static final String MAIN_SERVLET_MAPPING_PATH = "/";

    //properties
    public static Properties property = new Properties();

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

    //logger messages
    public static final String EXCEPTION_MESSAGE = "Exception - ";
    public static final String HTTP_METHOD_MESSAGE = " HTTP Method - ";
    public static final String PRE_INTERCEPTOR_HANDLER = "PRE MET ----";
    public static final String POST_INTERCEPTOR_HANDLER = "POST MET ----";

    //exception
    public static final String CONNECTION_WITH_DB_LOST_EXCEPTION_LABEL = "Connection with db lost";
    public static final String NULL_SQL_QUERY_EXCEPTION_LABEL = "Sql query for this parameter(s) returns null";
    public static final String JSON_PARSER_EXCEPTION_LABEL = "Object can't parse to JSON format";
    public static final String RESPONSE_EXCEPTION_LABEL = "An input or output exception occurred in response";
    public static final String DAO_OPERATION_EXCEPTION_LABEL = "Sql statement dropped an exception";
    public static final String REQUEST_EXCEPTION_LABEL = "An input or output exception occurred in request";
    public static final String FILE_JSON_EXCEPTION_LABEL = "Cannot import this file to json";
    public static final String REQUEST_PARSER_EXCEPTION_LABEL = "Incorrect parameter(s) in request";
    public static final String AUTH_EXCEPTION_LABEL = "Cannot auth(some user authed now)";
    public static final String REQUEST_HEADER_EXCEPTION_LABEL = "Wrong header(s) in request";

    //frame special keys
    public static final String READ = "r";
}