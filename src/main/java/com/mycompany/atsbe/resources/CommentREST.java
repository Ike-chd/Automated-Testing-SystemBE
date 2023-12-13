package com.mycompany.atsbe.resources;

import Models.Communication.Comment;
import Services.CommentHandler;
import Services.ServicesInterfaces.CommentService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("comments")
public class CommentREST {
    
    private CommentService cs = new CommentHandler();
    
    @Path("addComment")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postComment(Comment comment) {
        return (cs.insertComment(comment)) ? Response.ok("Comment added").status(Response.Status.CREATED).build()
                : Response.ok("Failed to add comment").status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("getComment")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("commentId") int id) {
        try{
        return Response.ok(cs.getComment(id).orElseThrow()).status(Response.Status.FOUND).build();
        } catch(NoSuchElementException e){
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }
}
