package com.mycompany.atsbe.resources;

import Models.QA.Answer;
import Services.AnswerHandler;
import Services.ServicesInterfaces.AnswerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

//@Path("/answers")
public class AnswerREST {

//    AnswerService as = new AnswerHandler();
//
//    @GET
//    @Path("/{answerID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAnswerById(@PathParam("answerID") int answerID) {
//        try {
//            return Response.ok(as.getAnswer(answerID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @POST
//    @Path("/createAnswer")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAnswer(Answer answer) {
//        try {
//            return (as.addAnswer(answer))
//                    ? Response.ok("Answer created").status(Response.Status.CREATED).build()
//                    : Response.ok("Answer not created").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PUT
//    @Path("/updateAnswer")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateAnswer(Answer answer) {
//        try {
//            return (as.updateAnswer(answer))
//                    ? Response.ok("Answer updated").status(Response.Status.OK).build()
//                    : Response.ok("Answer not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("/deleteAnswer/{answerID}")
//    public Response deleteAnswer(@PathParam("answerID") int answerID) {
//        try {
//            return (as.deleteAnswer(answerID))
//                    ? Response.ok("Answer deleted").status(Response.Status.OK).build()
//                    : Response.ok("Answer not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
