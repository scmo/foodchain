import {Component, ElementRef, ViewChild} from '@angular/core';

import {NavController, NavParams, Platform} from 'ionic-angular';
import {GoogleMapsLatLng, GoogleMap, GoogleMapsEvent} from "ionic-native";




@Component({
  selector: 'page-farm',
  templateUrl: 'farm.html',
})
export class FarmComponent {
  public farm:any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public platform: Platform) {
    this.farm = navParams.get('farm');
  }
}
