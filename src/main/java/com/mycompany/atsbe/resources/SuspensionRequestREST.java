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

@Path("suspension-requests")
public class SuspensionRequestREST {

    private SuspensionRequestService srs = new SuspensionRequestHandler();

    @Path("admitsrq")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertSuspensionRequest(SuspensionRequest ssRequest) {
        boolean success = srs.insertSuspensionRequest(ssRequest);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("updatesrq/{ssrID}/{confirmID}/{confirmed}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSuspensionRequest(@PathParam("ssrID")int ssrID, @PathParam("confirmID")int confirmID, @PathParam("confirmed")int confirmed) {
        boolean success = srs.confirmSuspensionRequest(ssrID, confirmID, confirmed);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
//
//    @GET
//    @Path("delete/{ssId}")
//    public Response deleteSuspensionRequest(@PathParam("ssId") int ssId) {
//        boolean success = suspensionRequestService.deleteSuspensionRequest(ssId);
//        if (success) {
//            return Response.noContent().build();
//        } else {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
    @GET
    @Path("getAllRequests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSuspensionRequests() {
        return Response.ok(srs.getAllSuspensionRequests()).status(Response.Status.CREATED).build();
    }
//
//    @GET
//    @Path("active")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllActiveSuspensionRequests() {
//        List<SuspensionRequest> activeSuspensionRequests = suspensionRequestService.getAllActiveSuspensionRequests();
//        return Response.ok().entity(activeSuspensionRequests).build();
//    }
//
//    @GET
//    @Path("by-student/{studentId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getSuspensionRequestsByStudent(@PathParam("studentId") int studentId) {
//        Student student = new Student();
//        student.setUserID(studentId);
//        List<SuspensionRequest> studentSuspensionRequests = suspensionRequestService.getSuspensionRequestsByStudent(student);
//        return Response.ok().entity(studentSuspensionRequests).build();
//    } 
    
    @Path("getUnApprovedSuspensionRequests")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnApprovedSuspensionRequests(){
        return Response.ok(srs.getAllUnApproved()).status(Response.Status.CREATED).build();
    }
    
    @Path("getNumOfUnApprovedSuspensionRequests")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNumOfUnApprovedSuspensionRequests(){
        return Response.ok(srs.getAllUnApproved().size()).status(Response.Status.CREATED).build();
    }
}
