import {Component, ViewChild} from '@angular/core';

import {NavController, NavParams} from 'ionic-angular';
import { Chart } from 'chart.js';

@Component({
  selector: 'page-cow',
  templateUrl: 'cow.html',
})
export class CowComponent {

  public cow:any;

  @ViewChild('barCanvas') barCanvas;
  barChart: any;

  @ViewChild('lineCanvas') lineCanvas;
  lineChart: any;

  @ViewChild('outsideGraph') outsideCanvas;
  outsideGraph: any;

  public hideStepStats:boolean;
  public hideOutsideStats:boolean;


  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.cow = navParams.get('cow');

    this.hideStepStats = true;
    this.hideOutsideStats = true;
  }


  toggleShowStepStats(event) {
    this.hideStepStats = !this.hideStepStats;
  }

  toggleOutsideStats(event) {
    this.hideOutsideStats = !this.hideOutsideStats;
  }

  ionViewDidLoad() {

    this.lineChart = new Chart(this.lineCanvas.nativeElement, {

      type: 'line',
      data: {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
          {
            label: "Steps of " + this.cow.name,
            fill: false,
            lineTension: 0.1,
            backgroundColor: "rgba(75,192,192,0.4)",
            borderColor: "rgba(75,192,192,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(75,192,192,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "rgba(75,192,192,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: [65, 59, 80, 81, 56, 55, 40],
            spanGaps: false,
          }
        ]
      }

    });


  }

}
