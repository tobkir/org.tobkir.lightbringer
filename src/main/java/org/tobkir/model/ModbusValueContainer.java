package org.tobkir.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ModbusValueContainer {

    private int batteryChargingState;
    private float consumptionFromBattery;
    private float consumptionFromGrid;
    private float consumptionFromPV;
    private float actualPVPower;
    private int batteryChargingPower;
    private float dailyYield;
    private ZonedDateTime timestamp;

    public ModbusValueContainer() {
        this.timestamp = ZonedDateTime.now(); // Set current time as default
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

    // Updated equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModbusValueContainer that = (ModbusValueContainer) o;
        return batteryChargingState == that.batteryChargingState &&
                Float.compare(that.consumptionFromBattery, consumptionFromBattery) == 0 &&
                Float.compare(that.consumptionFromGrid, consumptionFromGrid) == 0 &&
                Float.compare(that.consumptionFromPV, consumptionFromPV) == 0 &&
                Float.compare(that.actualPVPower, actualPVPower) == 0 &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batteryChargingState, consumptionFromBattery, consumptionFromGrid, consumptionFromPV, actualPVPower, timestamp);
    }

    @Override
    public String toString() {
        return "ModbusValueContainer{" +
                "batteryChargingState=" + batteryChargingState +
                ", consumptionFromBattery=" + consumptionFromBattery +
                ", consumptionFromGrid=" + consumptionFromGrid +
                ", consumptionFromPV=" + consumptionFromPV +
                ", actualPVPower=" + actualPVPower +
                ", timestamp=" + timestamp +
                '}';
    }

    public int getBatteryChargingPower() {
        return batteryChargingPower;
    }

    public void setBatteryChargingPower(int batteryChargingPower) {
        this.batteryChargingPower = batteryChargingPower;
    }

    public float getDailyYield() {
        return dailyYield;
    }

    public void setDailyYield(float dailyYield) {
        this.dailyYield = dailyYield;
    }
}