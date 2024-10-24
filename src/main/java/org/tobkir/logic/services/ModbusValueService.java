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
        List<BatteryState> batteryStates = modbusValueDAO.findAllBatteryChargingStates(start, end);

        batteryStates = batteryStates.stream()
                .sorted(Comparator.comparing(BatteryState::getTimestamp))
                .toList();

        List<BatteryState> filteredStates = new ArrayList<>();
        ZonedDateTime lastTimestamp = null;

        for (BatteryState state : batteryStates) {
            if (lastTimestamp == null || Duration.between(lastTimestamp, state.getTimestamp()).toMinutes() >= 2) {
                filteredStates.add(state);
                lastTimestamp = state.getTimestamp();
            }
        }
        return filteredStates;
    }

    public List<Float> getAllConsumptionFromBattery() {
        return modbusValueDAO.findAllConsumptionFromBattery();
    }

    public List<Float> getAllConsumptionFromGrid() {
        return modbusValueDAO.findAllConsumptionFromGrid();
    }

    public List<PvConsumptionState> getAllConsumptionFromPV(ZonedDateTime start, ZonedDateTime end) {
        List<PvConsumptionState> pvPowerStates = modbusValueDAO.findAllConsumptionFromPV(start, end);
        pvPowerStates = pvPowerStates.stream()
                .sorted(Comparator.comparing(PvConsumptionState::getTimestamp))
                .toList();

        List<PvConsumptionState> filteredStates = new ArrayList<>();
        ZonedDateTime lastTimestamp = null;

        for (PvConsumptionState state : pvPowerStates) {
            if (lastTimestamp == null || Duration.between(lastTimestamp, state.getTimestamp()).toMinutes() >= 2) {
                filteredStates.add(state);
                lastTimestamp = state.getTimestamp();
            }
        }

        return filteredStates;
    }

    public List<PvPowerState> getAllActualPVPower(ZonedDateTime start, ZonedDateTime end) {
        List<PvPowerState> pvPowerStates = modbusValueDAO.findAllActualPVPower(start, end);

        pvPowerStates = pvPowerStates.stream()
                .sorted(Comparator.comparing(PvPowerState::getTimestamp))
                .toList();

        List<PvPowerState> filteredStates = new ArrayList<>();
        ZonedDateTime lastTimestamp = null;

        for (PvPowerState state : pvPowerStates) {
            if (lastTimestamp == null || Duration.between(lastTimestamp, state.getTimestamp()).toMinutes() >= 2) {
                filteredStates.add(state);
                lastTimestamp = state.getTimestamp();
            }
        }
        return filteredStates;
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