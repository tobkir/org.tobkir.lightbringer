import {Component, OnDestroy, OnInit} from '@angular/core';
import {AreaChartModule, Color, NumberCardModule, ScaleType} from "@swimlane/ngx-charts";
import {ValueService} from "../../services/logic/value.service";
import {PvState} from "../../model/pv-state.model";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {NgIf} from "@angular/common";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {PvConsumption} from "../../model/pv-consumption.model";

@Component({
  selector: 'app-pv',
  standalone: true,
  imports: [
    AreaChartModule,
    MatProgressSpinner,
    NgIf,
    FlexLayoutModule,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatIcon,
    NumberCardModule
  ],
  templateUrl: './pv.component.html',
  styleUrl: './pv.component.scss'
})
export class PvComponent implements OnInit, OnDestroy {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestInterval: any;
  consumptionInterval: any;
  colorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#4cf394', '#3893a8']
  };
  values: any[] = [];
  legend: boolean = true;
  animations: boolean = true;
  xAxis: boolean = false;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  yAxisLabel: string = 'PV Leistung / Verbrauch';
  timeline: boolean = true;

  constructor(
    private valueService: ValueService
  ) {
  }

  ngOnInit() {
    this.setPowerValues();
    //this.setConsumptionValues();
    this.requestInterval = setInterval(this.setPowerValues.bind(this), 30000);
    this.consumptionInterval = setInterval(this.setConsumptionValues.bind(this), 30000);
  }

  setPowerValues = (start?: Date, end?: Date) => {
    if (!start) {
      start = this.startOfDay;
    }
    if (!end) {
      end = this.endOfDay;
    }

    this.valueService.getPvStates(start, end).subscribe(entries => {
      let series: any[] = [];

      entries.forEach((entry: PvState) => {
        series.push({name: entry.timestamp, value: entry.actualPVPower})
      })
      this.values.push(
        {name: "PV Leistung", series: [...series]}
      );
      this.setConsumptionValues(start, end)
    });
  }
  setConsumptionValues = (start?: Date, end?: Date) => {
    if (!start) {
      start = this.startOfDay;
    }
    if (!end) {
      end = this.endOfDay;
    }

    this.valueService.getPvConsumptionStates(start, end).subscribe(entries => {
      let series: any[] = [];

      entries.forEach((entry: PvConsumption) => {
        series.push({name: entry.timestamp, value: entry.actualPVConsumption})
      })
      this.values.push(
        {name: "Verbrauch aus PV", series: [...series]}
      );
    });
  }


  ngOnDestroy() {
    console.log("Weg mit de viecher")
    clearInterval(this.requestInterval);
    clearInterval(this.consumptionInterval);
  }

}
