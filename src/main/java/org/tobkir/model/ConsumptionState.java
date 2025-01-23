package org.tobkir.model;

import java.time.ZonedDateTime;

public class ConsumptionState {
    private Float consumptionFromBattery;
    private Float consumptionFromGrid;
    private Float consumptionFromPV;
    private Float totalConsumption;
    private Float totalDailyConsumption;
    private ZonedDateTime timestamp;

    public ConsumptionState(Float consumptionFromBattery,
                            Float consumptionFromGrid,
                            Float consumptionFromPV,
                            ZonedDateTime timestamp) {
        this.consumptionFromBattery = consumptionFromBattery;
        this.consumptionFromGrid = consumptionFromGrid;
        this.consumptionFromPV = consumptionFromPV;
        this.timestamp = timestamp;
    }

    public Float getConsumptionFromBattery() {
        return consumptionFromBattery;
    }

    public void setConsumptionFromBattery(Float consumptionFromBattery) {
        this.consumptionFromBattery = consumptionFromBattery;
    }

    public Float getConsumptionFromGrid() {
        return consumptionFromGrid;
    }

    public void setConsumptionFromGrid(Float consumptionFromGrid) {
        this.consumptionFromGrid = consumptionFromGrid;
    }

    public Float getConsumptionFromPV() {
        return consumptionFromPV;
    }

    public void setConsumptionFromPV(Float consumptionFromPV) {
        this.consumptionFromPV = consumptionFromPV;
    }

    public Float getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Float totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Float getTotalDailyConsumption() {
        return totalDailyConsumption;
    }

    public void setTotalDailyConsumption(Float totalDailyConsumption) {
        this.totalDailyConsumption = totalDailyConsumption;
    }
}
