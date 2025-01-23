package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.tobkir.logic.persistence.ModbusValueDAO;
import org.tobkir.model.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service class for handling operations related to Modbus values.
 * This class provides methods to interact with ModbusValueDAO to perform CRUD operations
 * and retrieve specific data related to battery charging states, consumption, and PV power.
 */
@RequestScoped
public class ModbusValueService {

    private final ModbusValueDAO modbusValueDAO;
    private final ConsumptionService consumptionService;

    @Inject
    public ModbusValueService(ModbusValueDAO modbusValueDAO, ConsumptionService consumptionService) {
        this.modbusValueDAO = modbusValueDAO;
        this.consumptionService = consumptionService;
    }


    public List<ModbusValueEntity> getAllModbusValues() {
        return modbusValueDAO.findAll();
    }

    public List<ConsumptionState> getAllConsumptionsByDateRange(ZonedDateTime start, ZonedDateTime end) {
        List<ConsumptionState> consumptionStates = modbusValueDAO.findAllConsumptionsByDateRange(start, end);
        consumptionStates.forEach(state -> {
            state.setTotalConsumption(
                    state.getConsumptionFromBattery() +
                            state.getConsumptionFromGrid() +
                            state.getConsumptionFromPV()
            );
        });
        consumptionStates.forEach(state -> {
            state.setTotalDailyConsumption(consumptionService.calculateDailyConsumption(consumptionStates, start));
        });
        return consumptionStates;
    }

    public List<BatteryState> getAllBatteryChargingStates(ZonedDateTime start, ZonedDateTime end) {
        return modbusValueDAO.findAllBatteryChargingStates(start, end);
    }

    public List<Float> getAllConsumptionFromBattery() {
        return modbusValueDAO.findAllConsumptionFromBattery();
    }

    public List<Float> getAllConsumptionFromGrid() {
        return modbusValueDAO.findAllConsumptionFromGrid();
    }

    public List<PvConsumptionState> getAllConsumptionFromPV(ZonedDateTime start, ZonedDateTime end) {
        return modbusValueDAO.findAllConsumptionFromPV(start, end);
    }

    public List<PvPowerState> getAllActualPVPower(ZonedDateTime start, ZonedDateTime end) {
        return modbusValueDAO.findAllActualPVPower(start, end);
    }

    public ModbusValueEntity saveModbusValue(ModbusValueEntity entity) {
        return modbusValueDAO.save(entity);
    }

    public List<ModbusValueEntity> getModbusValuesByTimeRange(ZonedDateTime start, ZonedDateTime end) {
        return modbusValueDAO.findByTimeRange(start, end);
    }

    public ModbusValueEntity getLatestModbusValue() {
        return modbusValueDAO.findLatest();
    }
}