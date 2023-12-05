package com.mycompany.atsbe.resources;

import Models.Tests.Test;
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
    public Response createTestAttempt(Test test) {
        try {
            return (tas.addTestAttempt(test))
                    ? Response.ok("Test Attempt created").status(Response.Status.CREATED).build()
                    : Response.ok("Test Attempt not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/updateTestAttempt/{attemptID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTestAttempt(@PathParam("attemptID") int attemptID, Test test) {
        try {
            return (tas.updateTestAttempt(attemptID, test))
                    ? Response.ok("Test Attempt updated").status(Response.Status.OK).build()
                    : Response.ok("Test Attempt not updated").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/deleteTestAttempt/{attemptID}")
    public Response deleteTestAttempt(@PathParam("attemptID") int testID) {
        try {
            return (tas.deleteTestAttempt(testID))
                    ? Response.ok("Test Attempt deleted").status(Response.Status.OK).build()
                    : Response.ok("Test Attempt not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
