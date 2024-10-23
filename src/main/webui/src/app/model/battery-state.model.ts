import {timestamp} from "rxjs";

export class BatteryState {

  private _batteryChargingState: number;
  private _timestamp: Date;


  constructor(batteryChargingState: number, timestamp: Date) {
    this._batteryChargingState = batteryChargingState;
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
}
