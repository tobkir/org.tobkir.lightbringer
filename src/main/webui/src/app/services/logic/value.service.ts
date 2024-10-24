import { Injectable } from '@angular/core';
import {BackendValueService} from "../backend/backend-value.service";
import {QueryParamDateService} from "../utils/query-param-date.service";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ValueService {

  apiUrl: string = "rest/v1/api/values"

  constructor(
    private backendService: BackendValueService,
    private queryParamDateService: QueryParamDateService
  ) {
  }

  getLatestValues() {
    let url = this.apiUrl.concat("/latest");
    return this.backendService.getLatestValues(url);
  }

  getBatteryState(start: Date, end: Date) {
    let url = this.apiUrl.concat("/battery-charging-states");
    let formattedStart = this.queryParamDateService.getDateQueryParamString(start)
    let formattedEnd = this.queryParamDateService.getDateQueryParamString(end)
    const params = new HttpParams().set('start', formattedStart).set('end', formattedEnd);
    return this.backendService.getBatteryState(url, params);
  }

  getPvStates(start: Date, end: Date) {
    let url = this.apiUrl.concat("/all-pv-power");
    let formattedStart = this.queryParamDateService.getDateQueryParamString(start)
    let formattedEnd = this.queryParamDateService.getDateQueryParamString(end)
    const params = new HttpParams().set('start', formattedStart).set('end', formattedEnd);
    return this.backendService.getPvStates(url, params);
  }

  getPvConsumptionStates(start: Date, end: Date) {
    let url = this.apiUrl.concat("/consumption-from-pv");
    let formattedStart = this.queryParamDateService.getDateQueryParamString(start)
    let formattedEnd = this.queryParamDateService.getDateQueryParamString(end)
    const params = new HttpParams().set('start', formattedStart).set('end', formattedEnd);
    return this.backendService.getPvConsumptionStates(url, params);
  }
}
