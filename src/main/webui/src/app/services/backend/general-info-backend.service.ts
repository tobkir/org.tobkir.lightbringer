import {Injectable} from '@angular/core';
import {ModbusValueContainer} from "../../model/modbus-value-container.model";
import {HttpClient} from "@angular/common/http";
import {GeneralInfoContainer} from "../../model/general-info-container.model";

@Injectable({
  providedIn: 'root'
})
export class GeneralInfoBackendService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getIp(url: string) {
    return this.httpClient.get<GeneralInfoContainer>(url);
  }
}
