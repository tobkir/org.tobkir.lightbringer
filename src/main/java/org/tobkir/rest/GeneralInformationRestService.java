package org.tobkir.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tobkir.logic.services.ModbusReaderService;

@Path("/rest/v1/api/general")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class GeneralInformationRestService {

    private final ModbusReaderService modbusReaderService;

    public GeneralInformationRestService(ModbusReaderService modbusReaderService) {
        this.modbusReaderService = modbusReaderService;
    }

    @GET
    @Path("/ip")
    public Response getAllModbusValues() {
        return Response.ok(modbusReaderService.getIp()).build();
    }

}
