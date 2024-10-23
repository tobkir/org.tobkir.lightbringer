package org.tobkir.model;

import java.time.ZonedDateTime;

public class BatteryState {

    private Integer batteryChargingState;
    private ZonedDateTime timestamp;

    public BatteryState(Integer batteryChargingState, ZonedDateTime timestamp) {
        this.batteryChargingState = batteryChargingState;
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
}
