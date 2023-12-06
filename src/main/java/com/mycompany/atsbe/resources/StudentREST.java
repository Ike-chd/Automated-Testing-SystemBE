package com.mycompany.atsbe.resources;

import Models.Users.Student;
import Services.CourseHandler;
import Services.ServicesInterfaces.CourseService;
import Services.ServicesInterfaces.StudentService;
import Services.ServicesInterfaces.TestService;
import Services.StudentHandler;
import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("Students")
public class StudentREST {
    StudentService ss = new StudentHandler();
    CourseService cs = new CourseHandler();
    
    @Path("postStudent/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postStudent(Student student, @PathParam("courseId")int courseId){
        return null;
    }
    
    @Path("getStudent/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id")int id){
        return null;
    }
    
    @Path("getStudent/e-mail/{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentByEmail(@PathParam("email")String email){
        return null;
    }
    
    @Path("allStudents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents(){
        return Response.ok().status(Response.Status.CREATED).build();
    }
    
    @Path("updateStudent")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(Student student){
        return Response.ok().status(Response.Status.CREATED).build();
    }
}