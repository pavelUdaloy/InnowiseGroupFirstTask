package by.exception.abstract_model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static by.util.TextLabels.AUTH_EXCEPTION_LABEL;
import static by.util.TextLabels.CONNECTION_WITH_DB_LOST_EXCEPTION_LABEL;
import static by.util.TextLabels.FILE_JSON_EXCEPTION_LABEL;
import static by.util.TextLabels.JSON_PARSER_EXCEPTION_LABEL;
import static by.util.TextLabels.NULL_SQL_QUERY_EXCEPTION_LABEL;
import static by.util.TextLabels.REQUEST_EXCEPTION_LABEL;
import static by.util.TextLabels.REQUEST_HEADER_EXCEPTION_LABEL;
import static by.util.TextLabels.REQUEST_PARSER_EXCEPTION_LABEL;
import static by.util.TextLabels.RESPONSE_EXCEPTION_LABEL;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes implements ExceptionErrorCode {
    CONNECTION_WITH_DB_LOST_EXCEPTION(CONNECTION_WITH_DB_LOST_EXCEPTION_LABEL),
    NULL_SQL_QUERY_EXCEPTION(NULL_SQL_QUERY_EXCEPTION_LABEL),
    JSON_PARSER_EXCEPTION(JSON_PARSER_EXCEPTION_LABEL),
    RESPONSE_EXCEPTION(RESPONSE_EXCEPTION_LABEL),
    REQUEST_EXCEPTION(REQUEST_EXCEPTION_LABEL),
    FILE_JSON_EXCEPTION(FILE_JSON_EXCEPTION_LABEL),
    REQUEST_PARSER_EXCEPTION(REQUEST_PARSER_EXCEPTION_LABEL),
    REQUEST_HEADER_EXCEPTION(REQUEST_HEADER_EXCEPTION_LABEL),
    AUTH_EXCEPTION(AUTH_EXCEPTION_LABEL);

    private final String code;
}
