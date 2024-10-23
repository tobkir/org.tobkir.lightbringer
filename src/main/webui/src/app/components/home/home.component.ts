import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subscription, tap} from "rxjs";
import {Color, NgxChartsModule, PieChartModule, ScaleType} from "@swimlane/ngx-charts";
import {NgIf} from "@angular/common";
import {ValueService} from "../../services/logic/value.service";
import {MatProgressSpinner} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    PieChartModule,
    NgIf,
    NgxChartsModule,
    MatProgressSpinner
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  private subscription: Subscription = new Subscription();
  latestInteval: any;
  values: any[] = [];
  pieValues: any[] = [];
  colorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#4cf394',
      '#25e4b1',
      '#37806f',
      '#06cc9d',
      '#3893a8',
      '#549298']
  };

  cardColor: string = '#383b45';

  constructor(
    private valueService: ValueService
  ) {
  }

  ngOnInit() {
    this.setValues();
    this.latestInteval = setInterval(this.setValues.bind(this), 3000);
  }

  setValues = () => {
    let sub = this.valueService.getLatestValues().subscribe(entry => {
      let totalConsumption = (entry.consumptionFromPV + entry.consumptionFromBattery + entry.consumptionFromGrid)
      let values = [
        {name: "PV Leistung", value: entry.actualPVPower},
        {name: "Verbrauch gesamt", value: totalConsumption}
      ]
      this.values = [...values];
      let pieValues = [
        {name: "aus PV", value: entry.consumptionFromPV},
        {name: "aus Speicher", value: entry.consumptionFromBattery},
        {name: "aus Netz", value: entry.consumptionFromGrid},
      ]
      this.pieValues = [...pieValues];
    });
    this.subscription.add(sub);
  }


  onSelect($event: any) {
    console.log("event")
  }

  ngOnDestroy() {
    console.log("Weg mit de viecher")
    clearInterval(this.latestInteval);
    this.subscription.unsubscribe();
  }
}
