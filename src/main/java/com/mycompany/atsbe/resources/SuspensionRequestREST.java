package com.mycompany.atsbe.resources;

import Models.Communication.SuspensionRequest;
import Services.ServicesInterfaces.SuspensionRequestService;
import Services.SuspensionRequestHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("/suspension-requests")
public class SuspensionRequestREST {

    SuspensionRequestService suspensionRequestService = new SuspensionRequestHandler();

    @GET
    @Path("/{ssid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuspensionRequestById(@PathParam("ssid") int ssid) {
        try {
            return Response.ok(suspensionRequestService.getSuspensionRequest(ssid).orElseThrow()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("createSuspensionRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSuspensionRequest(SuspensionRequest suspensionRequest) {
        try {
            return (suspensionRequestService.addSuspensionRequest(suspensionRequest))
                    ? Response.ok("Suspension Request created").status(Response.Status.CREATED).build()
                    : Response.ok("Suspension Request not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("updateSuspensionRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSuspensionRequest(SuspensionRequest suspensionRequest) {
        try {
            return (suspensionRequestService.updateSuspensionRequest(suspensionRequest))
                    ? Response.ok("Suspension Request updated").status(Response.Status.OK).build()
                    : Response.ok("Suspension Request not updated").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("deleteSuspensionRequest/{ssid}")
    public Response deleteSuspensionRequest(@PathParam("ssid") int ssid) {
        try {
            return (suspensionRequestService.deleteSuspensionRequest(ssid))
                    ? Response.ok("Suspension Request deleted").status(Response.Status.OK).build()
                    : Response.ok("Suspension Request not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
