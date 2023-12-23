package com.mycompany.atsbe.resources;

import Models.Courses.Course;
import Models.Courses.Module;
import Services.CourseHandler;
import Services.ServicesInterfaces.CourseService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;

@Path("courses")
public class CourseREST {

    CourseService cs = new CourseHandler();

    @Path("postCourse")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCourse(Course course) {
        return (cs.addCourse(course))
                ? Response.ok("Course Created").status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("getCourse/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("id") int id) {
        try {
            return Response.ok(cs.getCourse(id).orElseThrow()).status(Response.Status.FOUND).build();
        } catch (NoSuchElementException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("updateCourse")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourse(Course course) {
        return (cs.updateCourse(course)) ? Response.ok().status(Response.Status.CREATED).build()
                : Response.ok().status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("deleteCourse/{courseId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@PathParam("courseId") int id) {
        return (cs.deleteCourse(id)) ? Response.ok("Course deleted").status(Response.Status.CREATED).build()
                : Response.ok("Course Not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("allCourses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourse() {
        return Response.ok(cs.getAllCourses()).status(Response.Status.CREATED).build();
    }
    
    @Path("updateModules/{courseID}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModules(List<Module> modules, @PathParam("courseID")int courseId) {
        return (cs.updateModules(courseId, modules)) ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
