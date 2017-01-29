import {Component, ViewChild, ElementRef} from "@angular/core";
import {NavController, NavParams} from "ionic-angular";
import {Chart} from "chart.js";
import {CowService} from "../../../providers/cow-service";
import * as moment from "moment";

@Component({
  selector: 'page-cow',
  templateUrl: 'cow.html',
  providers: [CowService]
})
export class CowComponent {

  public cow:any;

  @ViewChild('barCanvas') barCanvas;
  barChart: any;

  @ViewChild('lineCanvas') lineCanvas;
  lineChart: any;

  @ViewChild('doughnutCanvas') doughnutCanvas;
  doughnutChart: any;

  @ViewChild('map') mapElement: ElementRef;
  map: any;

  public hideStepStats:boolean;
  public hideOutsideStats:boolean;
  public hideFarmer:boolean;
  public hideHistory:boolean;


  constructor(public navCtrl: NavController, public navParams: NavParams, public cowService:CowService) {

    this.cow = {};
    this.hideStepStats = false;
    this.hideOutsideStats = false;
    this.hideFarmer = false;
    this.loadCow(navParams.get('cowId'));
  }

  loadCow(id){
    this.cowService.loadCow(id).then(data => {
      this.cow = data;
      this.getOutdoorChart();
      this.getLineChart();
      this.loadMap();
    });
  }


  toggleShowStepStats(event) {
    this.hideStepStats = !this.hideStepStats;
  }

  toggleOutsideStats(event) {
    this.hideOutsideStats = !this.hideOutsideStats;
  }

  toggleFarmer(event) {
    this.hideFarmer = !this.hideFarmer;
  }

  toggleHistory(event) {
    this.hideHistory = !this.hideHistory;
  }

  getOutdoorChart(){

    let outdoor = 0;
    let indoor = 0;

    for(let i = 0; i < this.cow.movementMeasurements.length; i++){
      if(this.cow.movementMeasurements[i].stepTypes == 'OUTSIDE') {
        outdoor += this.cow.movementMeasurements[i].nrOfSteps;
      }else{
        indoor += this.cow.movementMeasurements[i].nrOfSteps;
      }
    }

    this.doughnutChart = new Chart(this.doughnutCanvas.nativeElement, {
      type: 'doughnut',
      data: {
        labels: ["Indoor", "Outdoor"],
        datasets: [{
          label: '# of Votes',
          data: [indoor, outdoor],
          backgroundColor: [
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          hoverBackgroundColor: [
            "#36A2EB",
            "#FFCE56"
          ]
        }]
      }

    });
  }

  getLineChart() {
    let data = [];
    let labels = [];

    for(let i = 0; i < this.cow.movementMeasurements.length; i++){
      data.push(this.cow.movementMeasurements[i].nrOfSteps)
      labels.push(moment(this.cow.movementMeasurements[i].timeEnd).format('MMM Do YYYY, hA'))
    }

    this.lineChart = new Chart(this.lineCanvas.nativeElement, {

      type: 'line',
      data: {
        labels: labels,
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
            data: data,
            spanGaps: false,
          }
        ]
      }

    });
  }


  loadMap(){
    let latLng = new google.maps.LatLng(this.cow.farm.latitude, this.cow.farm.longitude);
    let mapOptions = {
      center: latLng,
      zoom: 12,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    this.addMarker();
  }

  addMarker(){

    let marker = new google.maps.Marker({
      map: this.map,
      animation: google.maps.Animation.DROP,
      position: this.map.getCenter()
    });

    let content = "<h4>Information!</h4>";



    this.addInfoWindow(marker, content);

  }

  addInfoWindow(marker, content){

    let infoWindow = new google.maps.InfoWindow({
      content: content
    });

    google.maps.event.addListener(marker, 'click', () => {
      infoWindow.open(this.map, marker);
    });
  }

}
