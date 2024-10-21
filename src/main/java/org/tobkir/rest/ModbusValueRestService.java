package org.tobkir.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tobkir.logic.services.ModbusValueService;
import org.tobkir.model.ModbusValueEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/rest/v1/api/values")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ModbusValueRestService {

    static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    private final ModbusValueService modbusValueService;

    @Inject
    public ModbusValueRestService(ModbusValueService modbusValueService) {
        this.modbusValueService = modbusValueService;
    }

    @GET
    public Response getAllModbusValues() {
        return Response.ok(modbusValueService.getAllModbusValues()).build();
    }

    @GET
    @Path("/battery-charging-states")
    public List<Integer> getAllBatteryChargingStates() {
        return modbusValueService.getAllBatteryChargingStates();
    }

    @GET
    @Path("/consumption-from-battery")
    public List<Float> getAllConsumptionFromBattery() {
        return modbusValueService.getAllConsumptionFromBattery();
    }

    @GET
    @Path("/consumption-from-grid")
    public List<Float> getAllConsumptionFromGrid() {
        return modbusValueService.getAllConsumptionFromGrid();
    }

    @GET
    @Path("/consumption-from-pv")
    public List<Float> getAllConsumptionFromPV() {
        return modbusValueService.getAllConsumptionFromPV();
    }

    @GET
    @Path("/actual-pv-power")
    public List<Float> getAllActualPVPower() {
        return modbusValueService.getAllActualPVPower();
    }

    @POST
    public void saveModbusValue(ModbusValueEntity entity) {
        modbusValueService.saveModbusValue(entity);
    }

    @GET
    @Path("/time-range")
    public List<ModbusValueEntity> getModbusValuesByTimeRange(
            @QueryParam("start") String startString,
            @QueryParam("end") String endString
    ) {
        ZonedDateTime start = ZonedDateTime.parse(startString, formatter.withZone(ZoneId.systemDefault()));
        ZonedDateTime end = ZonedDateTime.parse(endString, formatter.withZone(ZoneId.systemDefault()));
        return modbusValueService.getModbusValuesByTimeRange(start, end);
    }

    @GET
    @Path("/latest")
    public ModbusValueEntity getLatestModbusValue() {
        return modbusValueService.getLatestModbusValue();
    }
}