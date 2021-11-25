package se.iths.exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException extends WebApplicationException {
    private final Response.Status statusCode;

    public ResourceNotFoundException(Response.Status statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getStatusCode();
    }

    public JsonObject jsonMessage() {
        return Json.createObjectBuilder().add("httpStatusCode", getStatusCode()).add("message", getMessage()).build();
    }
}