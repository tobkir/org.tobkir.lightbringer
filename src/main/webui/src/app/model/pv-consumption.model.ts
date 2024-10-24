export class PvConsumption {

  private _actualPVConsumption: number;
  private _timestamp: Date;


  constructor(actualPVPower: number, timestamp: Date) {
    this._actualPVConsumption = actualPVPower;
    this._timestamp = timestamp;
  }


  get actualPVConsumption(): number {
    return this._actualPVConsumption;
  }

  set actualPVConsumption(value: number) {
    this._actualPVConsumption = value;
  }

  get timestamp(): Date {
    return this._timestamp;
  }

  set timestamp(value: Date) {
    this._timestamp = value;
  }
}
