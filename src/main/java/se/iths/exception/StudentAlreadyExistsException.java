package se.iths.exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class StudentAlreadyExistsException extends RuntimeException {
    private final Response.Status statusCode;

    public StudentAlreadyExistsException(String message) {
        super(message);
        this.statusCode = Response.Status.CONFLICT;
    }

    public int getStatusCode() {
        return statusCode.getStatusCode();
    }

    public JsonObject jsonMessage() {
        return Json.createObjectBuilder().add("httpStatusCode", getStatusCode()).add("message", getMessage()).build();
    }
}
