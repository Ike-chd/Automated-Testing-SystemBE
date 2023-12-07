package com.mycompany.atsbe.resources;

import Models.QA.Question;
import Services.AnswerHandler;
import Services.QuestionHandler;
import Services.ServicesInterfaces.AnswerService;
import Services.ServicesInterfaces.QuestionService;
import Services.ServicesInterfaces.TopicService;
import Services.TopicHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("questions")
public class QuestionREST {

    QuestionService qs = new QuestionHandler();
    TopicService ts = new TopicHandler();
    AnswerService as = new AnswerHandler();

    @Path("postQuestion/{topicId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postQuestion(Question question, @PathParam("topicId") int id) {
        try {
            question.setTopic(ts.getTopic(id).orElseThrow());
            as.addAnswers(question.getAnswers());
        } catch (NoSuchElementException e) {
            return Response.ok("Topic not found").status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return (qs.addQuestion(question)) ? Response.ok("created").status(Response.Status.CREATED).build()
                : Response.ok("not created").status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("getQuestion/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestion(@PathParam("id") int id) {
        try {
            return Response.ok(qs.getQuestion(id).orElseThrow()).status(Response.Status.CREATED).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    public Response getAllQuestions(){
        return null;
    }
    
    public Response getAllQuestionsByTopic(){
        return null;
    }
}