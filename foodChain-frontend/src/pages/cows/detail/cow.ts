import { Component } from '@angular/core';

import {NavController, NavParams} from 'ionic-angular';

@Component({
  selector: 'page-cow',
  templateUrl: 'cow.html',
})
export class CowComponent {
  public cow:any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.cow = navParams.get('cow');
  }


}
