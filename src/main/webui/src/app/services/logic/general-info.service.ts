import { Injectable } from '@angular/core';
import {GeneralInfoBackendService} from "../backend/general-info-backend.service";
import {GeneralInfoContainer} from "../../model/general-info-container.model";

@Injectable({
  providedIn: 'root'
})
export class GeneralInfoService {

  apiUrl: string = "rest/v1/api/general"

  constructor(
    private backendService: GeneralInfoBackendService,
  ) {
  }

  getIp() {
    let url = this.apiUrl.concat("/ip");
    return this.backendService.getIp(url);
  }
}
