package org.tobkir.model;

import java.time.ZonedDateTime;

public class PvConsumptionState {
    private Float actualPVConsumption;
    private ZonedDateTime timestamp;

    public PvConsumptionState(Float actualPVConsumption, ZonedDateTime timestamp) {
        this.actualPVConsumption = actualPVConsumption;
        this.timestamp = timestamp;
    }

    public float getActualPVConsumption() {
        return actualPVConsumption;
    }

    public void setActualPVConsumption(float actualPVConsumption) {
        this.actualPVConsumption = actualPVConsumption;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
