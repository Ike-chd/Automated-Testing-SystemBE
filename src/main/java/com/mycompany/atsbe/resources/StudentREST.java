package com.mycompany.atsbe.resources;

import Services.ServicesInterfaces.TestService;
import com.google.gson.Gson;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/Students/{studentId}")
public class StudentREST {
    TestService testSV;
    
    @Path("/{testID}")
    public Response getTest(@PathParam("testID") int testID){
        Response.ResponseBuilder response = Response.accepted();
        String test = new Gson().toJson(testSV.getTest(testID));
        return response
                .entity(test)
                .build();
    }
    
    @Path("/{commentID}")
    public Response getComment(@PathParam("commentID") int commentID){
        Response.ResponseBuilder response = Response.accepted();
        return response.build();
    }
}