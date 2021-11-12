package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("students")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) throws WebApplicationException {
        Student foundStudent = studentService.findStudentById(id);
        return Response.ok(foundStudent).build();
    }

    @Path("get-all-students-by-lastname")
    @GET
    public Response findStudentByLastName(@QueryParam("lastName") String lastName) throws WebApplicationException {
        List<Student> foundStudents = studentService.findAllStudentsByLastName(lastName);
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() throws WebApplicationException {
        List<Student> allStudents = studentService.getAllStudents();
        return Response.ok(allStudents).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) throws WebApplicationException {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
