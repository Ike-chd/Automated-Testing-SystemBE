package com.mycompany.atsbe.resources;

import Models.Communication.Comment;
import Services.CommentHandler;
import Services.ServicesInterfaces.CommentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("comments")
public class CommentREST {

    private final CommentService cs = new CommentHandler();

    @Path("allComments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        return Response.ok(cs.getAllComments()).status(Response.Status.CREATED).build();
    }
    
    @Path("stu/allComments/{stuID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStuComments(@PathParam("stuID")int stuID) {
        return Response.ok(cs.getAllCommentsForMe(stuID)).status(Response.Status.CREATED).build();
    }
    
    @Path("stu/NumOfAllComments/{stuID}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response NumOfAllStuComments(@PathParam("stuID")int stuID) {
        return Response.ok(cs.getAllCommentsForMe(stuID).size()).status(Response.Status.CREATED).build();
    }
    
    @Path("fac/allComments/{facID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFacComments(@PathParam("facID")int facID) {
        return Response.ok(cs.getAllCommentsIMade(facID)).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("postComment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postComment(Comment comment) {
        try {
            return (cs.insertComment(comment))
                    ? Response.ok("Comment created").status(Response.Status.CREATED).build()
                    : Response.ok("Comment not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Path("deleteComment/{commentID}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("commentId") int commentId) {
        return cs.deleteComment(commentId) ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
