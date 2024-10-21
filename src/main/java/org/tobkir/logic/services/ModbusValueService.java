package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.tobkir.logic.persistence.ModbusValueDAO;
import org.tobkir.model.ModbusValueEntity;

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

    @Inject
    public ModbusValueService(ModbusValueDAO modbusValueDAO) {
        this.modbusValueDAO = modbusValueDAO;
    }


    public List<ModbusValueEntity> getAllModbusValues() {
        return modbusValueDAO.findAll();
    }

    public List<Integer> getAllBatteryChargingStates() {
        return modbusValueDAO.findAllBatteryChargingStates();
    }

    public List<Float> getAllConsumptionFromBattery() {
        return modbusValueDAO.findAllConsumptionFromBattery();
    }

    public List<Float> getAllConsumptionFromGrid() {
        return modbusValueDAO.findAllConsumptionFromGrid();
    }

    public List<Float> getAllConsumptionFromPV() {
        return modbusValueDAO.findAllConsumptionFromPV();
    }

    public List<Float> getAllActualPVPower() {
        return modbusValueDAO.findAllActualPVPower();
    }

    public void saveModbusValue(ModbusValueEntity entity) {
        modbusValueDAO.save(entity);
    }

    public List<ModbusValueEntity> getModbusValuesByTimeRange(ZonedDateTime start, ZonedDateTime end) {
        return modbusValueDAO.findByTimeRange(start, end);
    }

    public ModbusValueEntity getLatestModbusValue() {
        return modbusValueDAO.findLatest();
    }
}