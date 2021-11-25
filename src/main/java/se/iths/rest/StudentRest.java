package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.StudentAlreadyExistsException;
import se.iths.exception.ResourceNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
    public Response createStudent(Student student) throws StudentAlreadyExistsException {
        studentService.createStudent(student);
        URI studentUriLocation = uriInfo.getAbsolutePathBuilder().path(Long.toString(student.getId())).build();
        return Response.created(studentUriLocation).entity(student).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() throws ResourceNotFoundException {
        List<Student> allStudents = studentService.getAllStudents();
        return Response.ok(allStudents).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) throws ResourceNotFoundException {
        Student foundStudent = studentService.findStudentById(id);
        return Response.ok(foundStudent).build();
    }

    @Path("search")
    @GET
    public Response findStudentByLastName(@QueryParam("lastName") String lastName) throws ResourceNotFoundException {
        List<Student> foundStudents = studentService.findAllStudentsByLastName(lastName);
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) throws ResourceNotFoundException {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStudentEmail(@PathParam("id") Long id, @QueryParam("email") String email) throws ResourceNotFoundException {
        Student updatedStudent = studentService.updateStudentEmail(id, email);
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) throws ResourceNotFoundException {
        studentService.deleteStudent(id);
        return Response.noContent().build();
    }
}
