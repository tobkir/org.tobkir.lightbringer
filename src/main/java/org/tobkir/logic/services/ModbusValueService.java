package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.tobkir.logic.persistence.ModbusValueDAO;
import org.tobkir.model.BatteryState;
import org.tobkir.model.ModbusValueEntity;
import org.tobkir.model.PvConsumptionState;
import org.tobkir.model.PvPowerState;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to Modbus values.
 * This class provides methods to interact with ModbusValueDAO to perform CRUD operations
 * and retrieve specific data related to battery charging states, consumption, and PV power.
 */
@RequestScoped
public class ModbusValueService {

    private final ModbusValueDAO modbusValueDAO;

    @Inject
    public ModbusValueService(ModbusValueDAO modbusValueDAO) {
        this.modbusValueDAO = modbusValueDAO;
    }


    public List<ModbusValueEntity> getAllModbusValues() {
        return modbusValueDAO.findAll();
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