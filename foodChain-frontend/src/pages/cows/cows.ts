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
        this.sumStep();
      });
  }

  sumStep(){
    for(let i = 0; i < this.cows.length; i++){
      this.cows[i].steps = 0
      for(let y = 0; y < this.cows[i].movementMeasurements.length; y++){
        this.cows[i].steps += this.cows[i].movementMeasurements[y].nrOfSteps
      }
    }
  }

  cowTapped(event, cow) {
    this.navCtrl.push(CowComponent, {
      cowId: cow.animalId
    });
  }
}
