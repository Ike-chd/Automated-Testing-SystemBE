package com.mycompany.atsbe.resources;

import Models.Reports.FacultyReport;
import Services.FacultyReportHandler;
import Services.ServicesInterfaces.FacultyReportService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("faculty-reports")
public class FacultyReportREST {

    FacultyReportService facultyReportService = new FacultyReportHandler();

//    @GET
//    @Path("/{reportID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getFacultyReportById(@PathParam("reportID") int reportID) {
//        try {
//            return Response.ok(facultyReportService.getFacultyReport(reportID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
    @POST
    @Path("createFacultyReport")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFacultyReport(FacultyReport facultyReport) {
        try {
            return (facultyReportService.addFacultyReport(facultyReport))
                    ? Response.ok("Faculty Report created").status(Response.Status.CREATED).build()
                    : Response.ok("Faculty Report not created").status(Response.Status.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PUT
//    @Path("updateFacultyReport/{reportID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateFacultyReport(@PathParam("reportID") int reportID, FacultyReport facultyReport) {
//        try {
//            return (facultyReportService.updateFacultyReport(reportID, facultyReport))
//                    ? Response.ok("Faculty Report updated").status(Response.Status.OK).build()
//                    : Response.ok("Faculty Report not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("deleteFacultyReport/{reportID}")
//    public Response deleteFacultyReport(@PathParam("reportID") int reportID) {
//        try {
//            return (facultyReportService.deleteFacultyReport(reportID))
//                    ? Response.ok("Faculty Report deleted").status(Response.Status.OK).build()
//                    : Response.ok("Faculty Report not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
