package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.ResourceNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Context
    UriInfo uriInfo;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        Subject subjectResult = subjectService.createSubject(subject);
        URI subjectsUriLocation = uriInfo.getAbsolutePathBuilder().path(Long.toString(subject.getId())).build();
        return Response.created(subjectsUriLocation).entity(subjectResult).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return Response.ok(subjects).build();
    }

    @Path("{id}")
    @GET
    public Response findSubjectById(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.findSubjectById(id);
        return Response.ok(foundSubject).build();
    }

    @Path("/{subjectId}/students/{studentId}")
    @POST
    public Response addStudentToSubject(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId)  throws ResourceNotFoundException {
        Subject updatedSubject = subjectService.addStudentToSubject(subjectId, studentId);
        return Response.ok(updatedSubject).build();
    }

    @Path("/{subjectId}/teachers/{teacherId}")
    @POST
    public Response addTeacherToSubject(@PathParam("subjectId") Long subjectId, @PathParam("teacherId") Long teacherId) {
        Subject updatedSubject = subjectService.addTeacherToSubject(subjectId, teacherId);
        return Response.ok(updatedSubject).build();
    }
}
