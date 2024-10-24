import {Component, OnInit} from '@angular/core';
import {NgIf} from "@angular/common";
import {Color, AreaChartModule, ScaleType, NumberCardModule} from "@swimlane/ngx-charts";
import {ValueService} from "../../services/logic/value.service";
import {BatteryState} from "../../model/battery-state.model";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {FlexModule} from "@angular/flex-layout";
import {ModbusValueContainer} from 'src/app/model/modbus-value-container.model';

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
    FlexModule,
    NumberCardModule
  ],
  templateUrl: './battery.component.html',
  styleUrl: './battery.component.scss'
})
export class BatteryComponent implements OnInit {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestBatteryStateInterval: any;
  powerColorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#3893a8']
  };
  stateColorScheme: Color = {
    name: "test", selectable: true, group: ScaleType.Linear,
    domain: ['#4cf394']
  };
  cardColor: string = '#383b45';

  values: any[] = [];
  batteryChargingPowerValues: any [] = []
  legend: boolean = false;
  animations: boolean = true;
  xAxis: boolean = false;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  yAxisStateLabel: string = 'Akkustand in %';
  yAxisPowerLabel: string = 'Ladeleistung in W';
  timeline: boolean = true;
  latestValue: any[] = [];

  constructor(
    private valueService: ValueService
  ) {
  }

  ngOnInit() {
    this.getValues()
    this.requestBatteryStateInterval = setInterval(this.getValues.bind(this), 30000);
  }

  setBatteryStateValues = (entries: BatteryState[]) => {
    let series: any[] = [];
    entries.forEach((entry: BatteryState) => {
      series.push({name: entry.timestamp, value: entry.batteryChargingState})
    })
    this.values.push(
      {"name": "Akkustand", "series": [...series]}
    );
  }

  getValues = (start?: Date, end?: Date) => {
    if (!start) {
      start = this.startOfDay;
    }
    if (!end) {
      end = this.endOfDay;
    }
    this.valueService.getBatteryState(start, end).subscribe(entries => {
      this.setBatteryStateValues(entries);
      this.setBatteryChargingPowerValues(entries);
    });
    this.valueService.getLatestValues().subscribe(
      entry => {
        this.latestValue= [
          {name: "Akkustand", value: entry.batteryChargingState},
          {name: "Aktuelle Ladeleistung", value: !entry.batteryChargingPower? 0 :entry.batteryChargingPower},
          {name: "Verbrauch aus Speicher", value: entry.consumptionFromBattery},
        ]
      }
    )
  }

  setBatteryChargingPowerValues = (entries: BatteryState[]) => {
    let series: any[] = [];
    entries.forEach((entry: BatteryState) => {
      series.push({name: entry.timestamp, value: !entry.batteryChargingPower ? 0 : entry.batteryChargingPower})
    })
    this.batteryChargingPowerValues.push(
      {"name": "Ladeleistung", "series": [...series]}
    );
  }

  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  ngOnDestroy() {
    console.log("Weg mit de viecher")
    clearInterval(this.requestBatteryStateInterval);
  }
}
