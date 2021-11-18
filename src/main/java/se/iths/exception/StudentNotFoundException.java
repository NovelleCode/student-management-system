package se.iths.exception;

import javax.ws.rs.core.Response;

public class StudentNotFoundException extends RuntimeException {
    private final Response.Status statusCode;

    public StudentNotFoundException(Response.Status statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getStatusCode();
    }
}
