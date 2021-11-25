package se.iths.exceptionmapper;

import se.iths.exception.ResourceNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response.status(e.getStatusCode())
                .entity(e.jsonMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
