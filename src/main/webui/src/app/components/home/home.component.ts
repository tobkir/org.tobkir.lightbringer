import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
// import {Color, NgxChartsModule, PieChartModule, ScaleType} from "@swimlane/ngx-charts";
import {NgIf} from "@angular/common";
import {ValueService} from "../../services/logic/value.service";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import { ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-home',
  imports: [
    NgIf,
    MatProgressSpinner,
    BaseChartDirective
  ],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  private subscription: Subscription = new Subscription();
  public polarAreaChartType: ChartType = 'polarArea';
  pieChartData: ChartData<'polarArea'> = {labels: [], datasets: []};
  public pieAreaLegend = true;
  latestInteval: any;
  values: any[] = [];
  pieValues: any[] = [];
  // colorScheme: Color = {
  //   name: "test", selectable: true, group: ScaleType.Linear,
  //   domain: ['#4cf394',
  //     '#25e4b1',
  //     '#37806f',
  //     '#06cc9d',
  //     '#3893a8',
  //     '#549298']
  // };

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
      let totalConsumption = (entry.consumptionFromPV + entry.consumptionFromBattery + Math.abs(entry.consumptionFromGrid))
      let values = [
        {name: "PV Leistung", value: entry.actualPVPower},
        {name: "Verbrauch gesamt", value: totalConsumption}
      ]
      this.values = [...values];
      let pieLables: string[] = ["aus PV", "aus Speicher", "aus Netz"]
      let pieValues = [
        {name: "aus PV", value: entry.consumptionFromPV},
        {name: "aus Speicher", value: entry.consumptionFromBattery},
        {name: "aus Netz", value: entry.consumptionFromGrid},
      ]
      this.pieValues = [...pieValues];
      this.pieChartData = {
        labels: pieLables,
        datasets: [
          {
            data: [entry.consumptionFromPV, entry.consumptionFromBattery, entry.consumptionFromGrid],
            backgroundColor: ['rgba(37,228,177,0.55)','rgba(55,128,111,0.55)','rgba(56,147,168,0.55)'],
            hoverBackgroundColor: ['rgba(37,228,177,0.75)','rgba(55,128,111,0.75)','rgba(56,147,168,0.75)']
          }
        ]
      }
    });
    this.subscription.add(sub);
  }


  onSelect($event: any) {
    console.log("event")
  }

  ngOnDestroy() {
    clearInterval(this.latestInteval);
    this.subscription.unsubscribe();
  }
}
