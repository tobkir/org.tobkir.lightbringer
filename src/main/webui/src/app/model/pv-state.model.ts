export class PvState {

  private _actualPVPower: number;
  private _timestamp: Date;


  constructor(actualPVPower: number, timestamp: Date) {
    this._actualPVPower = actualPVPower;
    this._timestamp = timestamp;
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
