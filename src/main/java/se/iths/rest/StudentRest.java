package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("students")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Context
    UriInfo uriInfo;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(student.getId()));
        return Response.created(uriBuilder.build())
                .entity(student)
                .build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) throws WebApplicationException {
        Student foundStudent = studentService.findStudentById(id);
        return Response.ok(foundStudent)
                .build();
    }

    @Path("get-all-students-by-lastname")
    @GET
    public Response findStudentByLastName(@QueryParam("lastName") String lastName) throws WebApplicationException {
        List<Student> foundStudents = studentService.findAllStudentsByLastName(lastName);
        return Response.ok(foundStudents)
                .build();
    }

    @Path("")
    @GET
    public Response getAllStudents() throws WebApplicationException {
        List<Student> allStudents = studentService.getAllStudents();
        return Response.ok(allStudents)
                .build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student)
                .build();
    }

    @Path("email/{id}")
    @PATCH
    public Response updateStudentEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        Student updatedStudent = studentService.updateStudentEmail(id, email);
        return Response.ok(updatedStudent)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) throws WebApplicationException {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
