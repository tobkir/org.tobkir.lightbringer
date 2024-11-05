import {Component, OnInit} from '@angular/core';
import {NgClass, NgIf, NgStyle} from "@angular/common";
import {
  AreaChartModule,
  Color,
  LegendPosition,
  NumberCardModule,
  PieChartModule,
  ScaleType
} from "@swimlane/ngx-charts";
import {ValueService} from "../../services/logic/value.service";
import {BatteryState} from "../../model/battery-state.model";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {FlexModule} from "@angular/flex-layout";
import {ModbusValueContainer} from 'src/app/model/modbus-value-container.model';
import {BatteryInfoContainer} from "../../model/battery-info-container.model";
import {MathService} from "../../services/utils/math.service";

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
    NumberCardModule,
    NgStyle,
    NgClass,
    PieChartModule
  ],
  templateUrl: './battery.component.html',
  styleUrl: './battery.component.scss'
})
export class BatteryComponent implements OnInit {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestBatteryStateInterval: any;
  powerColorScheme: Color = {
    name: "power", selectable: true, group: ScaleType.Linear,
    domain: ['#3893a8']
  };
  consumptionColorScheme: Color = {
    name: "power", selectable: true, group: ScaleType.Linear,
    domain: ['#a83849']
  };
  stateColorScheme: Color = {
    name: "state", selectable: true, group: ScaleType.Linear,
    domain: [
      '#4cf394',
      '#365480'
    ]
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
  yAxisConsumptionLabel: string = 'Verbrauch in W';
  timeline: boolean = true;
  latestValue: ModbusValueContainer | undefined;
  batteryInfos: BatteryInfoContainer | undefined;
  batteryInfoValues: any [] = [];
  consumptionValues: any [] = [];
  legendPosition: string = 'below';

  constructor(
    private valueService: ValueService,
    protected mathService: MathService
  ) {
  }

  ngOnInit() {
    this.getValues()
    this.requestBatteryStateInterval = setInterval(this.getValues.bind(this), 30000);
  }

  setBatteryStateValues = (entries: BatteryState[]) => {
    let stateSeries: any[] = []
    let consumptionSeries: any[] = []
    entries.forEach((entry: BatteryState) => {
      stateSeries.push({name: entry.timestamp, value: entry.batteryChargingState})
      consumptionSeries.push({name: entry.timestamp, value: !entry.batteryConsumption ? 0 : entry.batteryConsumption})
    });
    this.values.push(
      {
        "name": "Akkustand",
        "series": [...stateSeries]
      }
    );
    this.consumptionValues.push(
      {
        "name": "Verbrauch aus Akku",
        "series": [...consumptionSeries]
      }
    )
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
      this.setLatestValues();
    });
    this.setBatteryInformations();
  }

  setBatteryInformations() {
    if(this.latestValue) {
      this.batteryInfoValues =
        [
          {name: "" + this.latestValue.batteryChargingState + " % voll", value: this.latestValue.batteryChargingState},
          {name: "" + (100 - this.latestValue.batteryChargingState) + " % leer", value: (100 - this.latestValue.batteryChargingState)}
        ]
    }
  }

  setLatestValues() {
    this.valueService.getLatestValues().subscribe(
      entry => {
        this.latestValue = entry;
        this.setBatteryInformations()
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

  protected readonly LegendPosition = LegendPosition;
}
