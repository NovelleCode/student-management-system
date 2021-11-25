package se.iths.exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class StudentAlreadyExistsException extends WebApplicationException {
    private final Response.Status statusCode;

    public StudentAlreadyExistsException(String message) {
        super(message, Response.Status.CONFLICT);
        this.statusCode = Response.Status.CONFLICT;
    }

    public int getStatusCode() {
        return statusCode.getStatusCode();
    }

    public JsonObject jsonMessage() {
        return Json.createObjectBuilder().add("httpStatusCode", getStatusCode()).add("message", getMessage()).build();
    }
}
