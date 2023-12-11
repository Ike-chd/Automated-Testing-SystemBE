package com.mycompany.atsbe.resources;

import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Services.ServicesInterfaces.TestAttemptService;
import Services.TestAttemptHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("/test-attempts")
public class TestAttemptREST {

    TestAttemptService tas = new TestAttemptHandler();

    @GET
    @Path("/{attemptID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestAttemptById(@PathParam("attemptID") int testID) {
        try {
            return Response.ok(tas.getTestAttempt(testID).orElseThrow()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/createTestAttempt")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTestAttempt(TestAttempt testAttempt) {
        try {
            return (tas.addTestAttempt(testAttempt))
                    ? Response.ok("Test Attempt created").status(Response.Status.CREATED).build()
                    : Response.ok("Test Attempt not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}