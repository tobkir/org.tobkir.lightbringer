import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {ModbusValueContainer} from "../../model/modbus-value-container.model";
import {BatteryState} from "../../model/battery-state.model";
import {PvState} from "../../model/pv-state.model";
import {PvConsumption} from "../../model/pv-consumption.model";

@Injectable({
  providedIn: 'root'
})
export class BackendValueService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getLatestValues(url: string): Observable<ModbusValueContainer>{
    return this.httpClient.get<ModbusValueContainer>(url);
  }

  getBatteryState(url: string, params: HttpParams): Observable<BatteryState[]> {
    return this.httpClient.get<BatteryState[]>(url, {params});
  }

  getPvStates(url: string, params: HttpParams) {
    return this.httpClient.get<PvState[]>(url, {params});
  }

  getPvConsumptionStates(url: string, params: HttpParams) {
    return this.httpClient.get<PvConsumption[]>(url, {params});
  }
}
