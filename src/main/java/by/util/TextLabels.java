package by.util;

import java.util.Properties;

public class TextLabels {

    //properties
    public static Properties property = new Properties();

    //images
    public static final String ADD_TO_IMAGES = "INSERT INTO simple.public.images VALUES (DEFAULT, ?, ?, ?)";
    public static final String DELETE_IMAGE = "DELETE FROM simple.public.images WHERE (owner_id=? AND name=?)";
    public static final String SELECT_ALL_IMAGES = "SELECT * FROM simple.public.images";
    public static final String SELECT_IMAGE = "SELECT * FROM simple.public.images WHERE (owner_id=? AND name=?)";
    public static final String SELECT_IMAGE_BY_ID = "SELECT * FROM simple.public.images WHERE id=?";
    public static final String SELECT_IMAGE_BY_OWNER_ID = "SELECT * FROM simple.public.images WHERE owner_id=?";
    public static final String DELETE_ALL_IMAGES = "DELETE FROM simple.public.images";
    public static final String NAME = "name";
    public static final String FILE_FORMAT = "file_format";

    //telephones
    public static final String ADD_TO_TELEPHONES = "INSERT INTO simple.public.telephones VALUES (DEFAULT, ?, ?)";
    public static final String DELETE_TELEPHONE = "DELETE FROM simple.public.telephones WHERE (owner_id=? AND number=?)";
    public static final String SELECT_ALL_TELEPHONES = "SELECT * FROM simple.public.telephones";
    public static final String SELECT_TELEPHONE = "SELECT * FROM simple.public.telephones WHERE (owner_id=? AND number=?)";
    public static final String SELECT_TELEPHONE_BY_OWNER_ID = "SELECT * FROM simple.public.telephones WHERE owner_id=?";
    public static final String DELETE_ALL_TELEPHONES = "DELETE FROM simple.public.telephones";
    public static final String UPDATE_TELEPHONE_BY_ID = "UPDATE simple.public.telephones SET number=? WHERE id=?";
    public static final String NUMBER = "number";

    //users
    public static final String ADD_TO_USERS = "INSERT INTO simple.public.users VALUES (DEFAULT, ?, ?, ?)";
    public static final String DELETE_USER = "DELETE FROM simple.public.users WHERE email=?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM simple.public.users";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM simple.public.users WHERE email=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM simple.public.users WHERE id=?";
    public static final String DELETE_ALL_USERS = "DELETE FROM simple.public.users";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";

    //car ads
    public static final String ADD_TO_CAR_ADS = "INSERT INTO simple.public.car_ads VALUES (?, ?, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?)";
    public static final String DELETE_CAR_AD = "DELETE FROM simple.public.car_ads WHERE id=?";
    public static final String SELECT_ALL_CAR_ADS = "SELECT * FROM simple.public.car_ads";
    public static final String SELECT_CAR_AD = "SELECT * FROM simple.public.car_ads WHERE (owner_id=? AND creation_date=?)";
    public static final String SELECT_CAR_AD_BY_ID = "SELECT * FROM simple.public.car_ads WHERE id=?";
    public static final String DELETE_ALL_CAR_ADS = "DELETE FROM simple.public.car_ads";
    public static final String SELECT_WITH_PAGINATION = "SELECT * FROM simple.public.car_ads ORDER BY creation_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    public static final String UPDATE_CAR_AD_AGE_BY_ID = "UPDATE simple.public.car_ads SET age=? WHERE id=?";
    public static final String UPDATE_CAR_AD_BRAND_BY_ID = "UPDATE simple.public.car_ads SET brand=? WHERE id=?";
    public static final String UPDATE_CAR_AD_MODEL_BY_ID = "UPDATE simple.public.car_ads SET model=? WHERE id=?";
    public static final String UPDATE_CAR_AD_ENGINE_BY_ID = "UPDATE simple.public.car_ads SET engine=? WHERE id=?";
    public static final String UPDATE_CAR_AD_POWER_BY_ID = "UPDATE simple.public.car_ads SET power=? WHERE id=?";
    public static final String UPDATE_CAR_AD_MILEAGE_BY_ID = "UPDATE simple.public.car_ads SET mileage=? WHERE id=?";
    public static final String UPDATE_CAR_AD_LAST_EDIT_DATE_BY_ID = "UPDATE simple.public.car_ads SET last_edit_date=? WHERE id=?";
    public static final String AGE = "age";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String CONDITION = "condition";
    public static final String MILEAGE = "mileage";
    public static final String ENGINE = "engine";
    public static final String POWER = "power";
    public static final String CREATION_DATE = "creation_date";
    public static final String LAST_EDIT_DATE = "last_edit_date";


    //common
    public static final String OWNER_ID = "owner_id";
    public static final String ID = "id";

    //properties
    public static final String PROPERTIES_PATH = "C:\\Users\\user\\IdeaProjects\\SimpleWeb\\src\\main\\resources\\properties.properties";
    public static final String PROPERTIES_DB_URL = "dbUrl";
    public static final String PROPERTIES_DB_DRIVER = "dbDriver";
    public static final String PROPERTIES_DB_USERNAME = "dbUsername";
    public static final String PROPERTIES_DB_PASSWORD = "dbPassword";
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
    public static final String ENGINE_SIZE_PARAM = "engineSize";
    public static final String ENGINE_POWER_PARAM = "enginePower";

    //logger messages
    public static final String FILTER_INIT = "MainFilter initialized";
    public static final String FILTER_DESTROY = "MainFilter destroyed";
    public static final String EXCEPTION_MESSAGE = "Exception - ";
    public static final String HTTP_METHOD_MESSAGE = " HTTP Method - ";

    //exception
    public static final String CONNECTION_WITH_DB_LOST_EXCEPTION_LABEL = "Connection with db lost";
    public static final String INCORRECT_SQL_PARAMETERS_EXCEPTION_LABEL = "Incorrect sql parameter(s)";
    public static final String NULL_SQL_QUERY_EXCEPTION_LABEL = "Sql query for this parameter(s) returns null";
    public static final String JSON_PARSER_EXCEPTION_LABEL = "Object can't parse to JSON format";
    public static final String RESPONSE_EXCEPTION_LABEL = "An input or output exception occurred in response";
    public static final String REQUEST_EXCEPTION_LABEL = "An input or output exception occurred in request";
    public static final String FILE_JSON_EXCEPTION_LABEL = "Cannot import this file to json";
    public static final String REQUEST_PARSER_EXCEPTION_LABEL = "Incorrect parameter(s) in request";

    //hikari properties
    public static final String CACHE_PREP_PROPERTY = "cachePrepStmts";
    public static final String CACHE_PREP_PROPERTY_VALUE = "true";
    public static final String PREP_CACHE_SIZE_PROPERTY = "prepStmtCacheSize";
    public static final String PREP_CACHE_SIZE_PROPERTY_VALUE = "250";
    public static final String PREP_CACHE_SQL_LIMIT_PROPERTY = "prepStmtCacheSqlLimit";
    public static final String PREP_CACHE_SQL_LIMIT_PROPERTY_VALUE = "2048";
    public static final String VALIDATION_PROPERTY = "validationQuery";
    public static final String VALIDATION_PROPERTY_VALUE = "SELECT 1";
}