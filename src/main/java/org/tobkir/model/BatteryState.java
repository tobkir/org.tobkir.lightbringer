package org.tobkir.model;

import java.time.ZonedDateTime;

public class BatteryState {

    private Integer batteryChargingState;
    private Integer batteryChargingPower;
    private ZonedDateTime timestamp;

    public BatteryState(Integer batteryChargingState, Integer batteryChargingPower, ZonedDateTime timestamp) {
        this.batteryChargingState = batteryChargingState;
        this.batteryChargingPower = batteryChargingPower;
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
}
