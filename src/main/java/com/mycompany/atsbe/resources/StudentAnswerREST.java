package com.mycompany.atsbe.resources;

import Models.StudentAnswer.StudentAnswer;
import Services.ServicesInterfaces.StudentAnswerService;
import Services.StudentAnswerHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("student-answers")
public class StudentAnswerREST {

    StudentAnswerService studentAnswerService = new StudentAnswerHandler();

//    @GET
//    @Path("{qaID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getStudentAnswerById(@PathParam("qaID") int qaID) {
//        try {
//            return Response.ok(studentAnswerService.getStudentAnswer(qaID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
    @POST
    @Path("createStudentAnswer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentAnswer(StudentAnswer studentAnswer) {
        try {
            return (studentAnswerService.addStudentAnswer(studentAnswer))
                    ? Response.ok("Student Answer created").status(Response.Status.CREATED).build()
                    : Response.ok("Student Answer not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PUT
//    @Path("updateStudentAnswer/{qaID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateStudentAnswer(@PathParam("qaID") int qaID, StudentAnswer studentAnswer) {
//        try {
//            return (studentAnswerService.updateStudentAnswer(qaID, studentAnswer))
//                    ? Response.ok("Student Answer updated").status(Response.Status.OK).build()
//                    : Response.ok("Student Answer not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("deleteStudentAnswer/{qaID}")
//    public Response deleteStudentAnswer(@PathParam("qaID") int qaID) {
//        try {
//            return (studentAnswerService.deleteStudentAnswer(qaID))
//                    ? Response.ok("Student Answer deleted").status(Response.Status.OK).build()
//                    : Response.ok("Student Answer not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
