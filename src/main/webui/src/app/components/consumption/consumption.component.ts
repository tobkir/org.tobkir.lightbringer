import {Component, OnInit} from '@angular/core';
import {ValueService} from "../../services/logic/value.service";
// import {AreaChartModule, Color, ScaleType} from "@swimlane/ngx-charts";
import {FlexModule} from "@angular/flex-layout";
import {
  MatCard,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {DatePipe, NgIf} from "@angular/common";
import {ConsumptionState} from "../../model/consumption-state.model";

@Component({
  selector: 'app-consumption',
  standalone: true,
  imports: [
    FlexModule,
    MatCard,
    MatCardContent,
    MatCardTitle,
    MatIcon,
    MatProgressSpinner,
    NgIf,
    MatCardHeader,
    MatCardSubtitle,
    MatCardAvatar,
    DatePipe
  ],
  templateUrl: './consumption.component.html',
  styleUrl: './consumption.component.scss'
})
export class ConsumptionComponent implements OnInit {
  //TODO Config mit Constructor
  animations: boolean = true;
  xAxis: boolean = false;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  yAxisLabelConsumption: string = 'Verbrauch';
  timeline: boolean = true;

  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);

  // colorSchemeConsumption: Color = {
  //   name: "consumption", selectable: true, group: ScaleType.Linear,
  //   domain: ['#a83849', '#4cf394']
  // };
  values: any[] = [];

  constructor(private valueService: ValueService) {
  }

  ngOnInit(): void {
    this.valueService.getConsumptionStates(this.startOfDay, this.endOfDay).subscribe(values => {
      let series: any[] = [];

      values.forEach((entry: ConsumptionState) => {
        series.push({name: entry.timestamp, value: entry.totalConsumption})
      })
      this.values.push(
        {name: "Verbrauch", series: [...series]}
      );
    })
  }
}
