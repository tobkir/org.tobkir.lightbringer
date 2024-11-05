package org.tobkir.model;

import java.time.ZonedDateTime;

public class PvPowerState {
    private Float actualPVPower;
    private Float dailyYield;
    private ZonedDateTime timestamp;

    public PvPowerState(Float actualPVPower, Float dailyYield, ZonedDateTime timestamp) {
        this.actualPVPower = actualPVPower;
        this.dailyYield = dailyYield;
        this.timestamp = timestamp;
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

    public Float getDailyYield() {
        return dailyYield;
    }

    public void setDailyYield(Float dailyYield) {
        this.dailyYield = dailyYield;
    }
}
