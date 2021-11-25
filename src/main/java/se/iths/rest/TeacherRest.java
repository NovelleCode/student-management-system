package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Context
    UriInfo uriInfo;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        Teacher teacherResult = teacherService.createTeacher(teacher);
        URI teacherUriLocation = uriInfo.getAbsolutePathBuilder().path(Long.toString(teacher.getId())).build();
        return Response.created(teacherUriLocation).entity(teacherResult).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return Response.ok(teachers).build();
    }

    @Path("{id}")
    @GET
    public Response findTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        return Response.ok(foundTeacher).build();
    }
}
