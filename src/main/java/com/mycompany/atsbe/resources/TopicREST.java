package com.mycompany.atsbe.resources;

import Models.Courses.Topic;
import Services.ServicesInterfaces.TopicService;
import Services.TopicHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("/topics")
public class TopicREST {

    TopicService tops = new TopicHandler();

    @Path("/postTopic")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTopic(Topic topic) {
        Response response = (tops.addTopic(topic)) ? Response.ok().status(Response.Status.CREATED).build()
                : Response.ok().status(Response.Status.CREATED).build();
        return response;
    }

    @Path("getTopic/{topicId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTopic(@PathParam("topicId") int id) {
        Topic topic;
        try {
            topic = tops.getTopic(id).orElseThrow();
        } catch (NoSuchElementException e) {
            return Response.ok(new Topic()).status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(topic).status(Response.Status.FOUND).build();
    }
}
