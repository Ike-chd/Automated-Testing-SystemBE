package com.mycompany.atsbe.resources;

import Models.Communication.Comment;
import Services.CommentHandler;
import Services.ServicesInterfaces.CommentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("comments")
public class CommentREST {

    private final CommentService commentService = new CommentHandler();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        return Response.ok(commentService.getAllComments()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postComment(Comment comment) {
        Comment createdComment = commentService.postComment(comment);
        return Response.status(Response.Status.CREATED).entity(createdComment).build();
    }

    @Path("{commentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("commentId") int commentId) {
        return commentService.getCommentById(commentId)
                .map(comment -> Response.ok(comment).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
