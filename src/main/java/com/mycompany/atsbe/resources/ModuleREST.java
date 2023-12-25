package com.mycompany.atsbe.resources;

import Services.ModuleHandler;
import Models.Courses.Module;
import Services.ServicesInterfaces.ModuleService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.NoSuchElementException;

@Path("modules")
public class ModuleREST {

    ModuleService ms = new ModuleHandler();

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getModule(int moduleId) {
//        try {
//            return Response.ok(ms.getModule(moduleId)).status(Response.Status.OK).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GET
//    @Path("getModule/{moduleID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getModuleById(@PathParam("moduleID") int moduleID) {
//        try {
//            return Response.ok(ms.getModule(moduleID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
  
    @Path("postModule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModule(Module module) {
        try {
            return (ms.addModule(module))
                    ? Response.ok("Module created").status(Response.Status.CREATED).build()
                    : Response.ok("Module not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("updateModule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(Module module) {
        return (ms.updateModule(module))
                ? Response.ok("Module updated").status(Response.Status.ACCEPTED).build()
                : Response.ok("Module not updated").status(Response.Status.NOT_ACCEPTABLE).build();
    }

    
    @Path("deleteModule/{moduleID}")
    @GET
    public Response deleteModule(@PathParam("moduleID") int moduleId) {
        try {
            return (ms.deleteModule(moduleId))
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
    public Response getAllModules() {
        try {
            return Response.ok(ms.getAllModules()).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("allModules/byCourse/{courseID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModulesByCourse(@PathParam("courseID")int id) {
        try {
            return Response.ok(ms.getAllModulesByCourse(id)).status(Response.Status.OK).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @Path("numberOfModules")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfModules(){
        return Response.ok(ms.getAllModules().size()).status(Response.Status.CREATED).build();
    }
}
