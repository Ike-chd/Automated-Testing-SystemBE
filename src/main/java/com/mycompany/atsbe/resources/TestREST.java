package com.mycompany.atsbe.resources;

import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;
import com.google.gson.Gson;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

//@Path("tests")
public class TestREST {

    TestService ts;

    @Path("postTest")
    public Response postTest() {
        Test test = ts.<Test>getTest(0).orElseGet(() -> new Test());
        return Response.ok(new Gson().toJson(test)).status(Response.Status.NOT_FOUND).build();
    }

    @Path("getTest/{testId}")
    public Response getTest(@PathParam("testId") int id) {
        Test test = ts.<Test>getTest(0).orElseGet(() -> new Test(0, "NotFound"));
        return Response.ok(test)
                .status((test.getTestID() == 0) ? Response.Status.NOT_FOUND : Response.Status.FOUND)
                .build();
    }
}
