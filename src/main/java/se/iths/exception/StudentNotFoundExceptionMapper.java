package se.iths.exception;

import org.json.simple.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundExceptionMapper implements ExceptionMapper<StudentNotFoundException> {

    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(jsonMapper(e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

    public JSONObject jsonMapper(String message) {
        JSONObject json = new JSONObject();
        json.put("httpErrorCode", "404");
        json.put("errorMessage", message);
        return json;
    }
}
