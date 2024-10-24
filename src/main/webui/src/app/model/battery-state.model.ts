export class BatteryState {


  private _batteryChargingState: number;
  private _batteryChargingPowerState: number;
  private _timestamp: Date;


  constructor(batteryChargingState: number, batteryChargingPowerState: number, timestamp: Date) {
    this._batteryChargingState = batteryChargingState;
    this._batteryChargingPowerState = batteryChargingPowerState;
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

  get batteryChargingPowerState(): number {
    return this._batteryChargingPowerState;
  }

  set batteryChargingPowerState(value: number) {
    this._batteryChargingPowerState = value;
  }

}
