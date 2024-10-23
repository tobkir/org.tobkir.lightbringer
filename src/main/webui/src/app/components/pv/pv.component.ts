import {Component, OnDestroy, OnInit} from '@angular/core';
import {AreaChartModule, Color, ScaleType} from "@swimlane/ngx-charts";
import {ValueService} from "../../services/logic/value.service";
import {BatteryState} from "../../model/battery-state.model";
import {PvState} from "../../model/pv-state.model";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-pv',
  standalone: true,
  imports: [
    AreaChartModule,
    MatProgressSpinner,
    NgIf
  ],
  templateUrl: './pv.component.html',
  styleUrl: './pv.component.scss'
})
export class PvComponent implements OnInit, OnDestroy {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestInterval: any;
  colorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#4cf394', '#000000']
  };

  values: any[] = [];
  legend: boolean = false;
  animations: boolean = true;
  xAxis: boolean = false;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  yAxisLabel: string = 'PV Leistung';
  timeline: boolean = true;

  constructor(
    private valueService: ValueService
  ) {
  }

  ngOnInit() {
    this.setValues();
    this.requestInterval = setInterval(this.setValues.bind(this), 30000);
  }

  setValues = (start?: Date, end?: Date) => {
    if (!start) {
      start = this.startOfDay;
    }
    if (!end) {
      end = this.endOfDay;
    }

    this.valueService.getPvStates(start, end).subscribe(entries => {
      let series: any[] = [];
      this.values.push(
        {name: "PV Leistung", series: [{}]}
      );
      entries.forEach((entry: PvState) => {
        series.push({name: entry.timestamp, value: entry.actualPVPower})
      })
      this.values[0].series = [...series]

    });
  }

  ngOnDestroy() {
    console.log("Weg mit de viecher")
    clearInterval(this.requestInterval);
  }

}
