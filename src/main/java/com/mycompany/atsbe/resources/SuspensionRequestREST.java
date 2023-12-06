package com.mycompany.atsbe.resources;

import Models.Communication.SuspensionRequest;
import Services.ServicesInterfaces.SuspensionRequestService;
import Services.SuspensionRequestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("/suspensionRequests")
public class SuspensionRequestREST {

    private SuspensionRequestService srs = new SuspensionRequestHandler();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSuspensionRequest(SuspensionRequest request) {
        try {
            boolean result = srs.addSuspensionRequest(request);
            if (result) {
                return Response.ok().status(Response.Status.CREATED).build();
            } else {
                return Response.ok().status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            return Response.ok().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{requestID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuspensionRequest(@PathParam("requestID") int requestID) {
        try {
            SuspensionRequest suspensionRequest = srs.getSuspensionRequest(requestID).orElseThrow();
            return Response.ok(suspensionRequest).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

}

