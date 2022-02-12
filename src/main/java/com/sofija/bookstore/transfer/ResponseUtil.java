package com.sofija.bookstore.transfer;

import org.springframework.http.HttpStatus;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response createResponse(Object data, int statusCode, String message) {
        Response response = createResponse();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setMessage(message);
        return response;
    }

    public static Response createResponse(int statusCode, String message) {
        return createResponse(null, statusCode, message);
    }

    public static Response createResponse(Object data, int statusCode) {
        return createResponse(data, statusCode, null);
    }

    public static Response createResponse(int statusCode) {
        return createResponse(null, statusCode, null);
    }

    public static Response createErrorResponse() {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error occurred");
    }

    private static Response createResponse() {
        return new Response();
    }
}
