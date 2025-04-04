package org.tobkir.logic.persistence;

import org.tobkir.model.*;

import java.time.ZonedDateTime;
import java.util.List;

public interface ModbusValueDAO {

    List<ModbusValueEntity> findAll();

    List<ConsumptionState> findAllConsumptionsByDateRange(ZonedDateTime start, ZonedDateTime end);

    List<BatteryState> findAllBatteryChargingStates(ZonedDateTime start, ZonedDateTime end);
    List<Float> findAllConsumptionFromBattery();
    List<Float> findAllConsumptionFromGrid();
    List<PvConsumptionState> findAllConsumptionFromPV(ZonedDateTime start, ZonedDateTime end);
    List<PvPowerState> findAllActualPVPower(ZonedDateTime start, ZonedDateTime end);

    ModbusValueEntity save(ModbusValueEntity entity);

    List<ModbusValueEntity> findByTimeRange(ZonedDateTime start, ZonedDateTime end);
    ModbusValueEntity findLatest();
}