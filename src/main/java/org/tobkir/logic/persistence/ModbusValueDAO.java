package org.tobkir.logic.persistence;

import org.tobkir.model.ModbusValueEntity;

import java.time.ZonedDateTime;
import java.util.List;

public interface ModbusValueDAO {

    List<ModbusValueEntity> findAll();

    List<Integer> findAllBatteryChargingStates();
    List<Float> findAllConsumptionFromBattery();
    List<Float> findAllConsumptionFromGrid();
    List<Float> findAllConsumptionFromPV();
    List<Float> findAllActualPVPower();

    void save(ModbusValueEntity entity);

    List<ModbusValueEntity> findByTimeRange(ZonedDateTime start, ZonedDateTime end);
    ModbusValueEntity findLatest();
}