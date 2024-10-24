import {timestamp} from "rxjs";

export class BatteryState {
  private _batteryChargingState: number;
  private _batteryConsumption: number;
  private _batteryChargingPower: number;
  private _timestamp: Date;


  constructor(batteryChargingState: number, batteryChargingPower: number, batteryConsumption:number, timestamp: Date) {
    this._batteryChargingState = batteryChargingState;
    this._batteryConsumption = batteryConsumption;
    this._batteryChargingPower = batteryChargingPower;
    this._timestamp = timestamp;
  }


  get batteryChargingState(): number {
    return this._batteryChargingState;
  }

  set batteryChargingState(value: number) {
    this._batteryChargingState = value;
  }

  get timestamp(): Date {
    return this._timestamp;
  }

  set timestamp(value: Date) {
    this._timestamp = value;
  }

  get batteryChargingPower(): number {
    return this._batteryChargingPower;
  }

  set batteryChargingPower(value: number) {
    this._batteryChargingPower = value;
  }

  get batteryConsumption(): number {
    return this._batteryConsumption;
  }

  set batteryConsumption(value: number) {
    this._batteryConsumption = value;
  }
}
