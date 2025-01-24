import { ChartConfiguration } from 'chart.js';

export const lineChartOptions: ChartConfiguration['options'] = {
  scales: {
    x: {
      display: false
    }
  },
  plugins: {
    legend: { display: false },
  },
  elements: {
    point: {
      radius: 0,
      hitRadius: 10
    }
  }
};
