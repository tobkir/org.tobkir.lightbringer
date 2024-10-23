package org.tobkir.logic.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.tobkir.logic.persistence.ModbusValueDAO;
import org.tobkir.model.BatteryState;
import org.tobkir.model.ModbusValueEntity;
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

        return new ArrayList<>(batteryStates.stream()
                .collect(Collectors.toMap(
                        BatteryState::getBatteryChargingState, // Verwende den Ladezustand als Schlüssel
                        state -> state, // Verwende das gesamte BatteryState-Objekt als Wert
                        (existing, replacement) -> existing // Bei Konflikten (gleicher Ladezustand) den ersten Wert behalten
                ))
                .values())
                .stream()
                .sorted(Comparator.comparing(BatteryState::getTimestamp)) // Nach Timestamp sortieren
                .collect(Collectors.toList());
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

    public List<PvPowerState> getAllActualPVPower(ZonedDateTime start, ZonedDateTime end) {
        List<PvPowerState> pvPowerStates = modbusValueDAO.findAllActualPVPower(start, end);

        pvPowerStates = pvPowerStates.stream()
                .sorted(Comparator.comparing(PvPowerState::getTimestamp))
                .collect(Collectors.toList());

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