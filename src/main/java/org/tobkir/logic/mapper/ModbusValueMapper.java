package org.tobkir.logic.mapper;

import jakarta.enterprise.context.RequestScoped;
import org.tobkir.model.ModbusValueContainer;
import org.tobkir.model.ModbusValueEntity;

@RequestScoped
public class ModbusValueMapper {

    /**
     * Maps a ModbusValueContainer to a ModbusValueEntity.
     *
     * @param container the ModbusValueContainer to map from
     * @return the mapped ModbusValueEntity
     */
    public ModbusValueEntity toEntity(ModbusValueContainer container) {
        if (container == null) {
            return null;
        }
        ModbusValueEntity entity = new ModbusValueEntity();
        entity.setBatteryChargingState(container.getBatteryChargingState());
        entity.setBatteryChargingPower(container.getBatteryChargingPower());
        entity.setConsumptionFromBattery(container.getConsumptionFromBattery());
        entity.setConsumptionFromGrid(container.getConsumptionFromGrid());
        entity.setConsumptionFromPV(container.getConsumptionFromPV());
        entity.setActualPVPower(container.getActualPVPower());
        entity.setTimestamp(container.getTimestamp());
        return entity;
    }

    /**
     * Maps a ModbusValueEntity to a ModbusValueContainer.
     *
     * @param entity the ModbusValueEntity to map from
     * @return the mapped ModbusValueContainer
     */
    public ModbusValueContainer toContainer(ModbusValueEntity entity) {
        if (entity == null) {
            return null;
        }
        ModbusValueContainer container = new ModbusValueContainer();
        container.setBatteryChargingState(entity.getBatteryChargingState());
        container.setConsumptionFromBattery(entity.getConsumptionFromBattery());
        container.setConsumptionFromGrid(entity.getConsumptionFromGrid());
        container.setConsumptionFromPV(entity.getConsumptionFromPV());
        container.setActualPVPower(entity.getActualPVPower());
        container.setTimestamp(entity.getTimestamp());
        entity.setBatteryChargingPower(container.getBatteryChargingPower());
        return container;
    }
}
