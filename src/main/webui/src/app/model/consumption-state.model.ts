import {timestamp} from "rxjs";

export class ConsumptionState {
  private _consumptionFromBattery: number;
  private _consumptionFromGrid: number;
  private _consumptionFromPV: number;
  private _totalConsumption: number;
  private _totalDailyConsumption: number;
  private _timestamp: Date;

  constructor(consumptionFromBattery: number, consumptionFromGrid: number, consumptionFromPV: number, totalConsumption: number, totalDailyConstumption: number, timestamp: Date) {
    this._consumptionFromBattery = consumptionFromBattery;
    this._consumptionFromGrid = consumptionFromGrid;
    this._consumptionFromPV = consumptionFromPV;
    this._totalConsumption = totalConsumption;
    this._totalDailyConsumption = totalDailyConstumption
    this._timestamp = timestamp;
  }

  get consumptionFromBattery(): number {
    return this._consumptionFromBattery;
  }

  set consumptionFromBattery(value: number) {
    this._consumptionFromBattery = value;
  }

  get consumptionFromGrid(): number {
    return this._consumptionFromGrid;
  }

  set consumptionFromGrid(value: number) {
    this._consumptionFromGrid = value;
  }

  get consumptionFromPV(): number {
    return this._consumptionFromPV;
  }

  set consumptionFromPV(value: number) {
    this._consumptionFromPV = value;
  }

  get totalConsumption(): number {
    return this._totalConsumption;
  }

  set totalConsumption(value: number) {
    this._totalConsumption = value;
  }

  get totalDailyConsumption(): number {
    return this._totalDailyConsumption;
  }

  set totalDailyConsumption(value: number) {
    this._totalDailyConsumption = value;
  }

  get timestamp(): Date {
    return this._timestamp;
  }

  set timestamp(value: Date) {
    this._timestamp = value;
  }
}
