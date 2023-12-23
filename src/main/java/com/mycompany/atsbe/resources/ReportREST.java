package com.mycompany.atsbe.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("reports")
public class ReportREST {
    @Path("hardestTopics")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHardestTopics(){
        return null;
    }
}