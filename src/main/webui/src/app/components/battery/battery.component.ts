import {Component, OnInit} from '@angular/core';
import {NgIf, NgStyle} from "@angular/common";
import {ValueService} from "../../services/logic/value.service";
import {BatteryState} from "../../model/battery-state.model";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCard, MatCardAvatar, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {ModbusValueContainer} from 'src/app/model/modbus-value-container.model';
import {MathService} from "../../services/utils/math.service";
import {BaseChartDirective} from 'ng2-charts';
import {ChartConfiguration, ChartType} from 'chart.js';
import {pieChartOptions} from "../../chart-options/PieChartOptions";

@Component({
  selector: 'app-battery',
  imports: [
    NgIf,
    MatProgressSpinnerModule,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatIcon,
    NgStyle,
    MatCardHeader,
    MatCardAvatar,
    BaseChartDirective
  ],
  standalone: true,
  templateUrl: './battery.component.html',
  styleUrl: './battery.component.scss'
})
export class BatteryComponent implements OnInit {
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);
  requestBatteryStateInterval: any;
  protected readonly pieChartOptions = pieChartOptions;
  // powerColorScheme: Color = {
  //   name: "power", selectable: true, group: ScaleType.Linear,
  //   domain: ['#3893a8']
  // };
  // consumptionColorScheme: Color = {
  //   name: "power", selectable: true, group: ScaleType.Linear,
  //   domain: ['#a83849']
  // };
  // stateColorScheme: Color = {
  //   name: "state", selectable: true, group: ScaleType.Linear,
  //   domain: [
  //     '#4cf394',
  //     '#365480'
  //   ]
  // };
  pieChartData: ChartConfiguration['data'] = {
    datasets: [],
    labels: []
  };
  pieChartType: ChartType = 'pie';

  values: any[] = [];
  batteryChargingPowerValues: any [] = []
  animations: boolean = true;
  latestValue: ModbusValueContainer | undefined;
  batteryInfoValues: any [] = [];
  consumptionValues: any [] = [];

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

    if (this.latestValue) {

      let labels: Array<string> =
        [
          "" + this.latestValue.batteryChargingState + " % voll",
          "" + (100 - this.latestValue.batteryChargingState) + " % leer"
        ];
      let data: { data: Array<number>, backgroundColor: Array<string>} = {
        data: [
          this.latestValue.batteryChargingState,
          (100 - this.latestValue.batteryChargingState)
        ],
        backgroundColor: [
          '#4cf394',
          '#365480'
        ]
      }

      this.pieChartData.datasets.push(data);
      this.pieChartData.labels = [...labels];
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
      series.push(
        {
          name: entry.timestamp,
          value: !entry.batteryChargingPower || entry.batteryChargingPower < 0 ? 0 : entry.batteryChargingPower
        }
      )
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
