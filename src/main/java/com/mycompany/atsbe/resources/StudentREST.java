package com.mycompany.atsbe.resources;

import Models.Users.Student;
import Services.ServicesInterfaces.StudentService;
import Services.StudentHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("students")
public class StudentREST {

    StudentService ss = new StudentHandler();

    @Path("createAccount")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postStudent(Student student) {
        try {
            return (ss.insertStudent(student)) ? 
                    Response.ok("student" + student.getName() + " " + student.getSurname() + "created")
                            .status(Response.Status.CREATED)
                            .build()
                    : Response.ok("Error in creating student")
                            .status(Response.Status.NOT_ACCEPTABLE)
                            .build();
        } catch (NoSuchElementException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("getStudentByid/{studentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("{studentId}")int id) {
        return null;
    }

    @Path("getStudent/email")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentByEmail(@PathParam("email") String email) {
        return null;
    }

    @Path("allStudents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        return Response.ok(ss.getAllStudent()).status(Response.Status.CREATED).build();
    }

    @Path("updateStudent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student student) {
        return Response.ok().status(Response.Status.CREATED).build();
    }
    
    @Path("numberOfStudents")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response numberOfStudents(){
        return Response.ok(ss.getAllStudent().size()).status(Response.Status.CREATED).build();
    }
}
