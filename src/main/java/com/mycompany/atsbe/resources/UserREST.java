package com.mycompany.atsbe.resources;

import Models.Users.FacultyMember;
import Models.Users.Admin;
import Models.Users.User;
import Services.ServicesInterfaces.UserService;
import Services.UserHandler;
import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/account")
public class UserREST {

    UserService us = new UserHandler();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(User user) {
        return Response.ok(new Gson().toJson(user)).build();
    }

    @Path("/request")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestAccount(User student) {
        us.addAccountRequest(student);
        return Response.ok(new Gson().toJson(student)).status(Response.Status.CREATED).build();
    }
    
    @Path("createAccount/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFacMAccount(Admin admin){
        return (us.addAccount(admin)) ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
    
    @Path("createAccount/facM")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdminAccount(FacultyMember facM){
        return (us.addAccount(facM)) ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
    
    @Path("/getUser/byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassword(@PathParam("email")String email){
        return Response.ok(us.getUserByEmial(email)).status(Response.Status.CREATED).build();
    }
}
