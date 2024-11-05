export class BatteryInfoContainer {

    private _batteryFirmware: String;
    private _batteryGrossCapacity: number;
    private _batteryManufacturer: String;
    private _batteryModelId: String;
    private _batterySerialNumber: String;
    private _batteryTemperature: number;
    private _numberOfBatteryCycles: number;


  constructor(batteryFirmware: String, batteryGrossCapacity: number, batteryManufacturer: String, batteryModelId: String, batterySerialNumber: String, batteryTemperature: number, numberOfBatteryCycles: number) {
    this._batteryFirmware = batteryFirmware;
    this._batteryGrossCapacity = batteryGrossCapacity;
    this._batteryManufacturer = batteryManufacturer;
    this._batteryModelId = batteryModelId;
    this._batterySerialNumber = batterySerialNumber;
    this._batteryTemperature = batteryTemperature;
    this._numberOfBatteryCycles = numberOfBatteryCycles;
  }

  get batteryFirmware(): String {
    return this._batteryFirmware;
  }

  set batteryFirmware(value: String) {
    this._batteryFirmware = value;
  }

  get batteryGrossCapacity(): number {
    return this._batteryGrossCapacity;
  }

  set batteryGrossCapacity(value: number) {
    this._batteryGrossCapacity = value;
  }

  get batteryManufacturer(): String {
    return this._batteryManufacturer;
  }

  set batteryManufacturer(value: String) {
    this._batteryManufacturer = value;
  }

  get batteryModelId(): String {
    return this._batteryModelId;
  }

  set batteryModelId(value: String) {
    this._batteryModelId = value;
  }

  get batterySerialNumber(): String {
    return this._batterySerialNumber;
  }

  set batterySerialNumber(value: String) {
    this._batterySerialNumber = value;
  }

  get batteryTemperature(): number {
    return this._batteryTemperature;
  }

  set batteryTemperature(value: number) {
    this._batteryTemperature = value;
  }

  get numberOfBatteryCycles(): number {
    return this._numberOfBatteryCycles;
  }

  set numberOfBatteryCycles(value: number) {
    this._numberOfBatteryCycles = value;
  }
}
