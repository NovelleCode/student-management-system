package se.iths.exceptionmapper;

import se.iths.exception.StudentAlreadyExistsException;
import se.iths.util.Mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentAlreadyExistsExceptionMapper implements ExceptionMapper<StudentAlreadyExistsException> {

    private final Mapper mapper = new Mapper();

    @Override
    public Response toResponse(StudentAlreadyExistsException e) {
        return Response.status(e.getStatusCode())
                .entity(mapper.jsonMapper(e.getStatusCode(), e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
