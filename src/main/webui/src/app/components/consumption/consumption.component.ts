import {Component, OnInit} from '@angular/core';
import {ValueService} from "../../services/logic/value.service";
// import {AreaChartModule, Color, ScaleType} from "@swimlane/ngx-charts";
import {
  MatCard,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle,
} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {DatePipe, NgIf} from "@angular/common";
import {ConsumptionState} from "../../model/consumption-state.model";
import {ChartData, ChartConfiguration, ChartType} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';
import {lineChartOptions} from "../../chart-options/LineChartOptions";

@Component({
  selector: 'app-consumption',
  standalone: true,
  imports: [
    MatCard,
    MatCardContent,
    MatCardTitle,
    MatIcon,
    MatProgressSpinner,
    NgIf,
    MatCardHeader,
    MatCardSubtitle,
    MatCardAvatar,
    DatePipe,
    BaseChartDirective,
  ],
  templateUrl: './consumption.component.html',
  styleUrl: './consumption.component.scss'
})
export class ConsumptionComponent implements OnInit {
  //TODO Config mit Constructor
  animations: boolean = true;
  public lineChartChartType: ChartType = 'line';
  public lineChartData: ChartConfiguration['data'] = {labels: [], datasets: []}
  startOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 0, 0, 0, 0);
  endOfDay = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59, 999);

  // colorSchemeConsumption: Color = {
  //   name: "consumption", selectable: true, group: ScaleType.Linear,
  //   domain: ['#a83849', '#4cf394']
  // };


  constructor(private valueService: ValueService) {
  }

  ngOnInit(): void {
    this.valueService.getConsumptionStates(this.startOfDay, this.endOfDay).subscribe(values => {
      let series: number[] = [];
      let labels: string[] = [];

      values.forEach((entry: ConsumptionState) => {
        labels.push(entry.timestamp.toString())
        series.push(entry.totalConsumption)
      })


      this.lineChartData =
        {
          labels: [...labels], datasets: [{
            data: [...series],
            label: 'Series A',
            backgroundColor: 'rgba(37,228,177,0.35)',
            hoverBackgroundColor: 'rgba(37,228,177,0.45)',
            fill: 'origin'
          }]
        }
    })
  }

  protected readonly lineChartOptions = lineChartOptions;
}
