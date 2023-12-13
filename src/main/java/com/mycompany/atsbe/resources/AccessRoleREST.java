package com.mycompany.atsbe.resources;

import Models.Users.AccessRole;
import Services.AccessRoleHandler;
import Services.ServicesInterfaces.AccessRoleService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("/access-roles")
public class AccessRoleREST {

    AccessRoleService accessRoleService = new AccessRoleHandler();

    @GET
    @Path("/{accessRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccessRoleById(@PathParam("accessRoleID") int accessRoleID) {
        try {
            return Response.ok(accessRoleService.getAccessRole(accessRoleID).orElseThrow()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/createAccessRole")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccessRole(AccessRole accessRole) {
        try {
            return (accessRoleService.addAccessRole(accessRole))
                    ? Response.ok("Access Role created").status(Response.Status.CREATED).build()
                    : Response.ok("Access Role not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/updateAccessRole/{accessRoleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccessRole(@PathParam("accessRoleID") int accessRoleID, AccessRole accessRole) {
        try {
            return (accessRoleService.updateAccessRole(accessRoleID, accessRole))
                    ? Response.ok("Access Role updated").status(Response.Status.OK).build()
                    : Response.ok("Access Role not updated").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/deleteAccessRole/{accessRoleID}")
    public Response deleteAccessRole(@PathParam("accessRoleID") int accessRoleID) {
        try {
            return (accessRoleService.deleteAccessRole(accessRoleID))
                    ? Response.ok("Access Role deleted").status(Response.Status.OK).build()
                    : Response.ok("Access Role not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
