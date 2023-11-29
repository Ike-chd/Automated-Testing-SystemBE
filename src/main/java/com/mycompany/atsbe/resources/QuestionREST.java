package com.mycompany.atsbe.resources;

import Models.Courses.Topic;
import Models.QA.Question;
import Services.QuestionHandler;
import Services.ServicesInterfaces.QuestionService;
import Services.ServicesInterfaces.TopicService;
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
    TopicService ts;

    @Path("postQuestion/{topicId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postQuestion(Question question, @PathParam("topicId") int id) {
        try {
            question.setTopic(ts.getTopic(id).orElseThrow());
        } catch (NoSuchElementException e) {
            return Response.ok("Topic not found").status(Response.Status.NOT_FOUND).build();
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
}
