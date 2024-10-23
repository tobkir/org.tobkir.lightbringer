package org.tobkir.logic.persistence;

import org.tobkir.model.BatteryState;
import org.tobkir.model.ModbusValueEntity;
import org.tobkir.model.PvPowerState;

import java.time.ZonedDateTime;
import java.util.List;

public interface ModbusValueDAO {

    List<ModbusValueEntity> findAll();

    List<BatteryState> findAllBatteryChargingStates(ZonedDateTime start, ZonedDateTime end);
    List<Float> findAllConsumptionFromBattery();
    List<Float> findAllConsumptionFromGrid();
    List<Float> findAllConsumptionFromPV();
    List<PvPowerState> findAllActualPVPower(ZonedDateTime start, ZonedDateTime end);

    void save(ModbusValueEntity entity);

    List<ModbusValueEntity> findByTimeRange(ZonedDateTime start, ZonedDateTime end);
    ModbusValueEntity findLatest();
}