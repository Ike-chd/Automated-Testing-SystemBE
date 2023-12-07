package com.mycompany.atsbe.resources;

import Models.Reports.AdminReport;
import Services.AdminReportHandler;
import Services.ServicesInterfaces.AdminReportService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

//@Path("/admin-reports")
public class AdminReportREST {

//    AdminReportService adminReportService = new AdminReportHandler();
//
//    @GET
//    @Path("/{reportID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAdminReportById(@PathParam("reportID") int reportID) {
//        try {
//            return Response.ok(adminReportService.getAdminReport(reportID).orElseThrow()).status(Response.Status.OK).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @POST
//    @Path("/createAdminReport")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAdminReport(AdminReport adminReport) {
//        try {
//            return (adminReportService.addAdminReport(adminReport))
//                    ? Response.ok("Admin Report created").status(Response.Status.CREATED).build()
//                    : Response.ok("Admin Report not created").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PUT
//    @Path("/updateAdminReport/{reportID}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateAdminReport(@PathParam("reportID") int reportID, AdminReport adminReport) {
//        try {
//            return (adminReportService.updateAdminReport(reportID, adminReport))
//                    ? Response.ok("Admin Report updated").status(Response.Status.OK).build()
//                    : Response.ok("Admin Report not updated").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Path("/deleteAdminReport/{reportID}")
//    public Response deleteAdminReport(@PathParam("reportID") int reportID) {
//        try {
//            return (adminReportService.deleteAdminReport(reportID))
//                    ? Response.ok("Admin Report deleted").status(Response.Status.OK).build()
//                    : Response.ok("Admin Report not deleted").status(Response.Status.NOT_ACCEPTABLE).build();
//        } catch (NoSuchElementException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
