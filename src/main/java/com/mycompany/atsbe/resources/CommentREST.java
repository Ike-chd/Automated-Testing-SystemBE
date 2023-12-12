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

    @Path("allComments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        return Response.ok(commentService.getAllComments()).build();
    }

    @Path("postComment")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postComment(Comment comment) {
        commentService.postComment(comment);
        return Response.ok("Comment posted").build();
    }

    @Path("{commentId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("commentId") int id) {
        return commentService.getCommentById(id)
                .map(comment -> Response.ok(comment).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
