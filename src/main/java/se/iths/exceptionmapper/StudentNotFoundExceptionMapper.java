package se.iths.exceptionmapper;

import se.iths.exception.StudentNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundExceptionMapper implements ExceptionMapper<StudentNotFoundException> {

    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.status(e.getStatusCode())
                .entity(e.jsonMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
