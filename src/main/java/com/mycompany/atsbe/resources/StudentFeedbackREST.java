package com.mycompany.atsbe.resources;

import Models.StudentFeedback.StudentFeedback;
import Services.StudentFeedbackHandler;
import Services.ServicesInterfaces.StudentFeedbackService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

//@Path("/student-feedbacks")
public class StudentFeedbackREST {

//    StudentFeedbackService studentFeedbackService = new StudentFeedbackHandler();
//
//    @GET
//    @Path("/{feedbackID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getStudentFeedbackById(@PathParam("feedbackID") int feedbackID) {
//        try {
//            return Response.ok(studentFeedbackService.getStudentFeedback(feedbackID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @POST
//    @Path("/createStudentFeedback")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createStudentFeedback(StudentFeedback studentFeedback) {
//        try {
//            return (studentFeedbackService.addStudentFeedback(studentFeedback))
//                    ? Response.ok("Student Feedback created").status(Response.Status.CREATED).build()
//                    : Response.ok("Student Feedback not created").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PUT
//    @Path("/updateStudentFeedback/{feedbackID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateStudentFeedback(@PathParam("feedbackID") int feedbackID, StudentFeedback studentFeedback) {
//        try {
//            return (studentFeedbackService.updateStudentFeedback(feedbackID, studentFeedback))
//                    ? Response.ok("Student Feedback updated").status(Response.Status.OK).build()
//                    : Response.ok("Student Feedback not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("/deleteStudentFeedback/{feedbackID}")
//    public Response deleteStudentFeedback(@PathParam("feedbackID") int feedbackID) {
//        try {
//            return (studentFeedbackService.deleteStudentFeedback(feedbackID))
//                    ? Response.ok("Student Feedback deleted").status(Response.Status.OK).build()
//                    : Response.ok("Student Feedback not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
