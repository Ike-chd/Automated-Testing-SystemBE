package com.mycompany.atsbe.resources;

import Services.ModuleHandler;
import Models.Courses.Module;
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
import java.util.NoSuchElementException;

@Path("modules")
public class ModuleREST {

    ModuleService moduleService = new ModuleHandler();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(int moduleId) {
        try {
            return Response.ok(moduleService.getModule(moduleId)).status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("getModule/{moduleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleById(@PathParam("moduleID") int moduleID) {
        try {
            return Response.ok(moduleService.getModule(moduleID).orElseThrow()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("createModule")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModule(Module module) {
        try {
            return (moduleService.addModule(module))
                    ? Response.ok("Module created").status(Response.Status.CREATED).build()
                    : Response.ok("Module not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("updateModule/{moduleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(Module module, @PathParam("moduleID") int moduleId) {
        try {
            return (moduleService.updateModule(module))
                    ? Response.ok("Module updated").status(Response.Status.OK).build()
                    : Response.ok("Module not updated").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("deleteModule/{moduleID}")
    public Response deleteModule(@PathParam("moduleID") int moduleId) {
        try {
            return (moduleService.deleteModule(moduleId))
                    ? Response.ok("Module deleted").status(Response.Status.OK).build()
                    : Response.ok("Module not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("allModules")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules(){
        try{
            return Response.ok(moduleService.getAllModules()).status(Response.Status.OK).build();
        } catch(NoSuchElementException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
