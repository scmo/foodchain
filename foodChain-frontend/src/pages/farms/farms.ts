import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import {CowService} from "../../providers/cow-service";
import {CowComponent} from "./detail/cow";
import {FarmService} from "../../providers/farm-service";
import {FarmComponent} from "./detail/farm";

@Component({
  selector: 'page-farms',
  templateUrl: 'farms.html',
  providers: [FarmService]
})
export class FarmsComponent {
  public farms:any;

  constructor(public navCtrl: NavController, public farmService: FarmService) {
    this.loadFarms();
  }

  loadFarms(){
    console.log("load Farms");
    this.farmService.load()
      .then(data => {
        this.farms = data;
      });
  }

  farmTapped(event, farm) {
    this.navCtrl.push(FarmComponent, {
      farm: farm
    });
  }
}
