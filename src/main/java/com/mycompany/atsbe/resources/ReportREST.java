package com.mycompany.atsbe.resources;

import Services.ReportHandler;
import Services.ServicesInterfaces.ReportService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("reports")
public class ReportREST {
    ReportService rs = new ReportHandler();
    
    @Path("mafs/{courseID}/{studentID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMAFS(@PathParam("courseID")int courseID, @PathParam("studentID")int studentID){
        return Response.ok(rs.getAllModulesAndAverageForEachPerStudent(courseID, studentID)).status(Response.Status.CREATED).build();
    }
    
    @Path("courseAVG/{courseID}/{studentID}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCAVG(@PathParam("courseID")int courseID, @PathParam("studentID")int studentID){
        return Response.ok(rs.getCourseAVG(courseID, studentID)).status(Response.Status.CREATED).build();
    }
    
    @Path("htopics")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHardestTopics(){
        return Response.ok(rs.getHardestTopics()).status(Response.Status.CREATED).build();
    }
    
    @Path("htopicsfs/{courseID}/{studentID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHardestTopicsfs(@PathParam("courseID")int courseID, @PathParam("studentID")int studentID){
        return Response.ok(rs.getHardestTopicsPerStudent(courseID, studentID)).status(Response.Status.CREATED).build();
    }
    
    @Path("htests/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHardestTests(){
        return Response.ok(rs.getHardestTests()).status(Response.Status.CREATED).build();
    }
    
    @Path("htestsfs/{courseID}/{studentID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHardestTestsfs(@PathParam("courseID")int courseID, @PathParam("studentID")int studentID){
        return Response.ok(rs.getHardestTestsPerStudent(courseID, studentID)).status(Response.Status.CREATED).build();
    }
}