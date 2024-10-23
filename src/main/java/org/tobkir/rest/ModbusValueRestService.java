package org.tobkir.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tobkir.logic.services.ModbusValueService;
import org.tobkir.model.ModbusValueEntity;
import org.tobkir.rest.logic.DateParserRestHelper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/rest/v1/api/values")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ModbusValueRestService {

    private final ModbusValueService modbusValueService;
    private final DateParserRestHelper dateParserRestHelper;

    @Inject
    public ModbusValueRestService(ModbusValueService modbusValueService, DateParserRestHelper dateParserRestHelper) {
        this.modbusValueService = modbusValueService;
        this.dateParserRestHelper = dateParserRestHelper;
    }

    @GET
    public Response getAllModbusValues() {
        return Response.ok(modbusValueService.getAllModbusValues()).build();
    }

    @GET
    @Path("/battery-charging-states")
    public Response getAllBatteryChargingStates(
            @QueryParam("start") String startString,
            @QueryParam("end") String endString
    ) {
        ZonedDateTime start = dateParserRestHelper.parseQueryParamStringToZDT(startString);
        ZonedDateTime end = dateParserRestHelper.parseQueryParamStringToZDT(endString);
        return Response.ok(modbusValueService.getAllBatteryChargingStates(start, end)).build();
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
    @Path("/all-pv-power")
    public Response getAllActualPVPower(
            @QueryParam("start") String startString,
            @QueryParam("end") String endString
    ) {
        ZonedDateTime start = dateParserRestHelper.parseQueryParamStringToZDT(startString);
        ZonedDateTime end = dateParserRestHelper.parseQueryParamStringToZDT(endString);
        return Response.ok(modbusValueService.getAllActualPVPower(start, end)).build();
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
        ZonedDateTime start = dateParserRestHelper.parseQueryParamStringToZDT(startString);
        ZonedDateTime end = dateParserRestHelper.parseQueryParamStringToZDT(endString);
        return modbusValueService.getModbusValuesByTimeRange(start, end);
    }

    @GET
    @Path("/latest")
    public ModbusValueEntity getLatestModbusValue() {
        return modbusValueService.getLatestModbusValue();
    }
}