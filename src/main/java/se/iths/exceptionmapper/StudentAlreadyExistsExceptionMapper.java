package se.iths.exceptionmapper;

import se.iths.exception.StudentAlreadyExistsException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentAlreadyExistsExceptionMapper implements ExceptionMapper<StudentAlreadyExistsException> {

    @Override
    public Response toResponse(StudentAlreadyExistsException e) {
        return Response.status(e.getStatusCode())
                .entity(e.jsonMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
