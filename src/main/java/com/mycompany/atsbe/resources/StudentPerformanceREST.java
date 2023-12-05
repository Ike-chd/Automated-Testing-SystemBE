package com.mycompany.atsbe.resources;

import Models.StudentPerformance.StudentPerformance;
import Services.StudentPerformanceHandler;
import Services.ServicesInterfaces.StudentPerformanceService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("/student-performances")
public class StudentPerformanceREST {

    StudentPerformanceService studentPerformanceService = new StudentPerformanceHandler();

    @GET
    @Path("/{performanceID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentPerformanceById(@PathParam("performanceID") int performanceID) {
        try {
            return Response.ok(studentPerformanceService.getStudentPerformance(performanceID).orElseThrow()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/createStudentPerformance")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentPerformance(StudentPerformance studentPerformance) {
        try {
            return (studentPerformanceService.addStudentPerformance(studentPerformance))
                    ? Response.ok("Student Performance created").status(Response.Status.CREATED).build()
                    : Response.ok("Student Performance not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/updateStudentPerformance/{performanceID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudentPerformance(@PathParam("performanceID") int performanceID, StudentPerformance studentPerformance) {
        try {
            return (studentPerformanceService.updateStudentPerformance(performanceID, studentPerformance))
                    ? Response.ok("Student Performance updated").status(Response.Status.OK).build()
                    : Response.ok("Student Performance not updated").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/deleteStudentPerformance/{performanceID}")
    public Response deleteStudentPerformance(@PathParam("performanceID") int performanceID) {
        try {
            return (studentPerformanceService.deleteStudentPerformance(performanceID))
                    ? Response.ok("Student Performance deleted").status(Response.Status.OK).build()
                    : Response.ok("Student Performance not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
