package org.tobkir.model;

import java.time.ZonedDateTime;

public class PvPowerState {
    private Float actualPVPower;
    private ZonedDateTime timestamp;

    public PvPowerState(Float actualPVPower, ZonedDateTime timestamp) {
        this.actualPVPower = actualPVPower;
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
}
