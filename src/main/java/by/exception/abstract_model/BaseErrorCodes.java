package by.exception.abstract_model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseErrorCodes implements ExceptionErrorCode {
    CONNECTION_WITH_DB_LOST_EXCEPTION("Connection with db lost"),
    INCORRECT_SQL_PARAMETERS_EXCEPTION("Incorrect sql parameter(s)"),
    NULL_SQL_QUERY_EXCEPTION("Sql query for this parameter(s) returns null"),
    JSON_PARSER_EXCEPTION("Object can't parse to JSON format"),
    RESPONSE_EXCEPTION("An input or output exception occurred in response"),
    REQUEST_EXCEPTION("An input or output exception occurred in request"),
    FILE_JSON_EXCEPTION("Cannot import this file to json"),
    REQUEST_PARSER_EXCEPTION("Incorrect parameter(s) in request");

    private final String code;
}
