import {Component, OnInit} from '@angular/core';
import {NgIf} from "@angular/common";
import {Color, AreaChartModule, ScaleType} from "@swimlane/ngx-charts";
import {ValueService} from "../../services/logic/value.service";
import {BatteryState} from "../../model/battery-state.model";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {FlexModule} from "@angular/flex-layout";

@Component({
  selector: 'app-battery',
  standalone: true,
  imports: [
    NgIf,
    AreaChartModule,
    MatProgressSpinnerModule,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatIcon,
    FlexModule
  ],
  templateUrl: './battery.component.html',
  styleUrl: './battery.component.scss'
})
export class BatteryComponent implements OnInit {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestBatteryStateInterval: any;
  requestBatteryChargingPowerInterval: any;
  colorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#4cf394', '#000000']
  };

  values: any[] = [];
  batteryChargingPowerValues: any [] = []
  legend: boolean = false;
  animations: boolean = true;
  xAxis: boolean = false;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  yAxisLabel: string = 'Akkustand in %';
  timeline: boolean = true;

  constructor(
    private valueService: ValueService
  ) {
  }

  ngOnInit() {
    this.setBatteryStateValues();
    this.setBatteryChargingPowerValues();
    this.requestBatteryStateInterval = setInterval(this.setBatteryStateValues.bind(this), 30000);
    this.requestBatteryChargingPowerInterval = setInterval(this.setBatteryStateValues.bind(this), 30000);
  }

  setBatteryStateValues = (start?: Date, end?: Date) => {
    if(!start){
      start = this.startOfDay;
    }
    if(!end){
      end = this.endOfDay;
    }

    this.valueService.getBatteryState(start, end).subscribe(entries => {
      let series: any[] = [];
      this.values.push(
        {name: "Speicher", series: [{}]}
      );
      entries.forEach((entry:BatteryState) => {
        series.push({name: entry.timestamp, value: entry.batteryChargingState})
      })
      this.values[0].series = [...series]
    });
  }

  setBatteryChargingPowerValues = (start?: Date, end?: Date) => {
    if(!start){
      start = this.startOfDay;
    }
    if(!end){
      end = this.endOfDay;
    }

    this.valueService.getBatteryState(start, end).subscribe(entries => {
      let series: any[] = [];
      this.batteryChargingPowerValues.push(
        {name: "Ladeleistung", series: [{}]}
      );
      entries.forEach((entry:BatteryState) => {
        series.push({name: entry.timestamp, value: entry.batteryChargingPowerState})
      })
      this.batteryChargingPowerValues[0].series = [...series]
    });
  }

  onSelect(data:any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data:any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data:any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  ngOnDestroy() {
    console.log("Weg mit de viecher")
    clearInterval(this.requestBatteryStateInterval);
  }
}
