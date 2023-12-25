package com.mycompany.atsbe.resources;

import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;
import Services.TestHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tests")
public class TestREST {

    TestService ts = new TestHandler();
    
    @Path("postTest")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTest(Test test) {
        return (ts.insertTest(test)) ? Response.ok().status(Response.Status.CREATED).build()
                : Response.ok().status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    @Path("getAllTests")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTests(){
        return Response.ok(ts.getAllTests()).status(Response.Status.CREATED).build();
    }
    
    @Path("getAllTests/byModule/{moduleID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTestsByModule(@PathParam("moduleID")int id){
        return Response.ok(ts.getAllTestsByModule(id)).status(Response.Status.CREATED).build();
    }
    
    @Path("getAllMyTests/{courseID}/{studentID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudentTests(@PathParam("courseID")int id, @PathParam("studentID")int stuID){
        return Response.ok(ts.allTestsInACourse(id, stuID)).status(Response.Status.CREATED).build();
    }
    
    @Path("getWeight/{moduleId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTotalWeight(@PathParam("moduleId") int moduleId){
        return Response.ok(ts.getTotalWeight(moduleId)).status(Response.Status.FOUND).build();
    }
    
    @Path("deleteTest/{testId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTest(@PathParam("{testId}") int testId){
        return (ts.deleteTest(testId)) ? Response.ok("Test deleted").status(Response.Status.ACCEPTED).build()
                : Response.ok("Failed to delete test").status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    @Path("updateTest")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTest(Test test){
        return (ts.updateTest(test)) ? Response.ok().status(Response.Status.OK).build()
                : Response.ok().status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("getTestTotal/{testId}")
    @GET
    public Response getTestTotal(@PathParam("{testId}") int testId){
        return Response.ok(ts.getTotalTestMarks(testId)).status(Response.Status.CREATED).build();
    }
    
    @Path("numberOfTests")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfTests(){
        return Response.ok(ts.getAllTests().size()).status(Response.Status.CREATED).build();
    }
}
