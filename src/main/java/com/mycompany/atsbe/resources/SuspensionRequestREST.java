package com.mycompany.atsbe.resources;

import Models.Communication.SuspensionRequest;
import Models.Users.Student;
import Services.ServicesInterfaces.SuspensionRequestService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("suspension-requests")
public class SuspensionRequestREST {

    private SuspensionRequestService suspensionRequestService;

    public SuspensionRequestREST(SuspensionRequestService suspensionRequestService) {
        this.suspensionRequestService = suspensionRequestService;
    }

    @GET
    @Path("getRequest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuspensionRequest(@PathParam("ssId") int ssId) {
        SuspensionRequest suspensionRequest = suspensionRequestService.getSuspensionRequest(ssId);
        if (suspensionRequest != null) {
            return Response.ok().entity(suspensionRequest).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSuspensionRequest(SuspensionRequest ssRequest) {
        boolean success = suspensionRequestService.insertSuspensionRequest(ssRequest);
        if (success) {
            return Response.status(Response.Status.CREATED).entity(ssRequest).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSuspensionRequest(@PathParam("ssId") int ssId, SuspensionRequest ssRequest) {
        ssRequest.setSsId(ssId);
        boolean success = suspensionRequestService.updateSuspensionRequest(ssRequest);
        if (success) {
            return Response.ok().entity(ssRequest).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("delete/{ssId}")
    public Response deleteSuspensionRequest(@PathParam("ssId") int ssId) {
        boolean success = suspensionRequestService.deleteSuspensionRequest(ssId);
        if (success) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("getAllRequests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSuspensionRequests() {
        List<SuspensionRequest> suspensionRequests = suspensionRequestService.getAllSuspensionRequests();
        return Response.ok().entity(suspensionRequests).build();
    }

    @GET
    @Path("active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActiveSuspensionRequests() {
        List<SuspensionRequest> activeSuspensionRequests = suspensionRequestService.getAllActiveSuspensionRequests();
        return Response.ok().entity(activeSuspensionRequests).build();
    }

    @GET
    @Path("by-student/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuspensionRequestsByStudent(@PathParam("studentId") int studentId) {
        Student student = new Student();
        student.setUserID(studentId);
        List<SuspensionRequest> studentSuspensionRequests = suspensionRequestService.getSuspensionRequestsByStudent(student);
        return Response.ok().entity(studentSuspensionRequests).build();
    }
}
