import {Component, ViewChild, ElementRef} from "@angular/core";
import {NavController, NavParams} from "ionic-angular";

declare var google;

@Component({
  selector: 'page-farm',
  templateUrl: 'farm.html',
})
export class FarmComponent {
  public farm:any;

  @ViewChild('map') mapElement: ElementRef;
  map: any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.farm = navParams.get('farm');
  }

  ionViewDidLoad() {
    this.loadMap();
  }

  loadMap(){
    let latLng = new google.maps.LatLng(this.farm.latitude, this.farm.longitude);
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
