import {Component} from "@angular/core";
import {NavController} from "ionic-angular";
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
