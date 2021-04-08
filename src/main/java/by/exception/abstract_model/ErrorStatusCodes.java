package by.exception.abstract_model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorStatusCodes implements ExceptionStatusCode {
    CONNECTION_WITH_DB_LOST_STATUS(3417),
    INCORRECT_SQL_PARAMETERS_STATUS(40197),
    NULL_SQL_QUERY_STATUS(49918),
    JSON_PARSER_STATUS(500),
    RESPONSE_STATUS(500),
    DAO_OPERATION_STATUS(49918),
    REQUEST_STATUS(400),
    FILE_JSON_STATUS(500),
    REQUEST_PARSER_STATUS(400),
    REQUEST_HEADER_STATUS(400),
    AUTH_STATUS(401);

    private final Integer status;
}
