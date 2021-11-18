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

        return Response.status(Response.Status.NOT_FOUND)
                .entity(mapper.jsonMapper("409", e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
