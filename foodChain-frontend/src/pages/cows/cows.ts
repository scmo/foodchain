import {Component} from "@angular/core";
import {NavController} from "ionic-angular";
import {CowService} from "../../providers/cow-service";
import {CowComponent} from "./detail/cow";

@Component({
  selector: 'page-cows',
  templateUrl: 'cows.html',
  providers: [CowService]
})
export class CowsComponent {
  public cows:any;

  constructor(public navCtrl: NavController, public cowService: CowService) {
    this.loadCows();
  }

  loadCows(){
    console.log("load Cows");
    this.cowService.load()
      .then(data => {
        this.cows = data;
      });
  }

  cowTapped(event, cow) {
    console.log(cow);
    this.navCtrl.push(CowComponent, {
      cowId: cow.animalId
    });
  }
}
