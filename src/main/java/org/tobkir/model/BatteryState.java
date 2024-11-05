package org.tobkir.model;

import java.time.ZonedDateTime;

public class BatteryState {

    private Integer batteryChargingState;
    private Integer batteryChargingPower;
    private Float batteryConsumption;
    private ZonedDateTime timestamp;

    public BatteryState(Integer batteryChargingState, Integer batteryChargingPower, Float batteryConsumption, ZonedDateTime timestamp) {
        this.batteryChargingState = batteryChargingState;
        this.batteryChargingPower = batteryChargingPower;
        this.batteryConsumption = batteryConsumption;
        this.timestamp = timestamp;
    }

    public int getBatteryChargingState() {
        return batteryChargingState;
    }

    public void setBatteryChargingState(int batteryChargingState) {
        this.batteryChargingState = batteryChargingState;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getBatteryChargingPower() {
        return batteryChargingPower;
    }

    public void setBatteryChargingPower(Integer batteryChargingPower) {
        this.batteryChargingPower = batteryChargingPower;
    }

    public Float getBatteryConsumption() {
        return batteryConsumption;
    }

    public void setBatteryConsumption(Float batteryConsumption) {
        this.batteryConsumption = batteryConsumption;
    }
}
