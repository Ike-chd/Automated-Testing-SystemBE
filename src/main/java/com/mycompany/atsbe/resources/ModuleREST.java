
package com.mycompany.atsbe.resources;

import Services.ModuleHandler;
import Services.ServicesInterfaces.ModuleService;
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
import java.util.Optional;
import Models.Courses.Module;

@Path("modules")
public class ModuleREST {

    private ModuleService moduleService = new ModuleHandler();

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("id") int id) {
        Optional<Module> module = moduleService.getModule(id);
        return module.map(value ->
                Response.ok(value).status(Response.Status.FOUND).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createModule(Module module) {
        boolean success = moduleService.addModule(module);

        if (success) {
            return Response.ok("Module Created").status(Response.Status.CREATED).build();
        } else {
            return Response.ok("Module Not Created").status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(Module module) {
        boolean success = moduleService.updateModule(module);

        if (success) {
            return Response.ok("Module Updated").status(Response.Status.OK).build();
        } else {
            return Response.ok("Module Not Updated").status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Path("delete")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(Module module) {
        boolean success = moduleService.deleteModule(module);

        if (success) {
            return Response.ok("Module Deleted").status(Response.Status.OK).build();
        } else {
            return Response.ok("Module Not Deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}

