package org.tobkir.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "modbus_values")
public class ModbusValueEntity {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private int batteryChargingState;
    private float consumptionFromBattery;
    private float consumptionFromGrid;
    private float consumptionFromPV;
    private float actualPVPower;
    private int batteryChargingPower;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timestamp;

    // Constructors
    public ModbusValueEntity() {
    }

    public ModbusValueEntity(ModbusValueContainer container, int batteryChargingPower) {
        this.batteryChargingState = container.getBatteryChargingState();
        this.consumptionFromBattery = container.getConsumptionFromBattery();
        this.consumptionFromGrid = container.getConsumptionFromGrid();
        this.consumptionFromPV = container.getConsumptionFromPV();
        this.actualPVPower = container.getActualPVPower();
        this.timestamp = container.getTimestamp();
        this.batteryChargingPower = batteryChargingPower;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public int getBatteryChargingState() {
        return batteryChargingState;
    }

    public void setBatteryChargingState(int batteryChargingState) {
        this.batteryChargingState = batteryChargingState;
    }

    public float getConsumptionFromBattery() {
        return consumptionFromBattery;
    }

    public void setConsumptionFromBattery(float consumptionFromBattery) {
        this.consumptionFromBattery = consumptionFromBattery;
    }

    public float getConsumptionFromGrid() {
        return consumptionFromGrid;
    }

    public void setConsumptionFromGrid(float consumptionFromGrid) {
        this.consumptionFromGrid = consumptionFromGrid;
    }

    public float getConsumptionFromPV() {
        return consumptionFromPV;
    }

    public void setConsumptionFromPV(float consumptionFromPV) {
        this.consumptionFromPV = consumptionFromPV;
    }

    public float getActualPVPower() {
        return actualPVPower;
    }

    public void setActualPVPower(float actualPVPower) {
        this.actualPVPower = actualPVPower;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getBatteryChargingPower() {
        return batteryChargingPower;
    }

    public void setBatteryChargingPower(int batteryChargingPower) {
        this.batteryChargingPower = batteryChargingPower;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModbusValueEntity that = (ModbusValueEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // toString method
    @Override
    public String toString() {
        return "ModbusValueEntity{" +
                "id=" + id +
                ", batteryChargingState=" + batteryChargingState +
                ", consumptionFromBattery=" + consumptionFromBattery +
                ", consumptionFromGrid=" + consumptionFromGrid +
                ", consumptionFromPV=" + consumptionFromPV +
                ", actualPVPower=" + actualPVPower +
                ", batteryChargingPower=" + batteryChargingPower +
                ", timestamp=" + timestamp +
                '}';
    }
}
