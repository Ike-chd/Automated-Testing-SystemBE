package com.mycompany.atsbe.resources;

import Models.StudentTest.StudentTest;
import Services.StudentTestHandler;
import Services.ServicesInterfaces.StudentTestService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("student-tests")
public class StudentTestREST {

    StudentTestService studentTestService = new StudentTestHandler();

//    @GET
//    @Path("{testID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getStudentTestById(@PathParam("testID") int testID) {
//        try {
//            return Response.ok(studentTestService.getStudentTest(testID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
    @POST
    @Path("createStudentTest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentTest(StudentTest studentTest) {
        try {
            return (studentTestService.addStudentTest(studentTest))
                    ? Response.ok("Student Test created").status(Response.Status.CREATED).build()
                    : Response.ok("Student Test not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PUT
//    @Path("updateStudentTest/{testID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateStudentTest(@PathParam("testID") int testID, StudentTest studentTest) {
//        try {
//            return (studentTestService.updateStudentTest(testID, studentTest))
//                    ? Response.ok("Student Test updated").status(Response.Status.OK).build()
//                    : Response.ok("Student Test not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("deleteStudentTest/{testID}")
//    public Response deleteStudentTest(@PathParam("testID") int testID) {
//        try {
//            return (studentTestService.deleteStudentTest(testID))
//                    ? Response.ok("Student Test deleted").status(Response.Status.OK).build()
//                    : Response.ok("Student Test not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
