export class ModbusValueContainer {

  private _batteryChargingState: number;
  private _consumptionFromBattery: number;
  private _consumptionFromGrid: number;
  private _consumptionFromPV: number;
  private _actualPVPower: number;
  private _timestamp: Date;

  constructor() {
    this._batteryChargingState = 0; // Standardwert, falls nicht gesetzt
    this._consumptionFromBattery = 0.0;
    this._consumptionFromGrid = 0.0;
    this._consumptionFromPV = 0.0;
    this._actualPVPower = 0.0;
    this._timestamp = new Date(); // Set current time as default
  }

  get batteryChargingState(): number {
    return this._batteryChargingState;
  }

  set batteryChargingState(value: number) {
    this._batteryChargingState = value;
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

  get actualPVPower(): number {
    return this._actualPVPower;
  }

  set actualPVPower(value: number) {
    this._actualPVPower = value;
  }

  get timestamp(): Date {
    return this._timestamp;
  }

  set timestamp(value: Date) {
    this._timestamp = value;
  }
}
