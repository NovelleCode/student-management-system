package se.iths.exception;

import se.iths.util.Mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundExceptionMapper implements ExceptionMapper<StudentNotFoundException> {

    private final Mapper mapper = new Mapper();

    @Override
    public Response toResponse(StudentNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(mapper.jsonMapper("404", e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
