import {Component, ViewChild} from "@angular/core";
import {Nav, Platform} from "ionic-angular";
import {StatusBar, Splashscreen, Deeplinks} from "ionic-native";
import {CowsComponent} from "../pages/cows/cows";
import {FarmsComponent} from "../pages/farms/farms";
import {Location} from "@angular/common";
import {CowComponent} from "../pages/cows/detail/cow";


@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = CowsComponent;

  pages: Array<{title: string, component: any, icon: string}>;

  constructor(public platform: Platform, location: Location) {
    // let routePath = location.path();
    // let split = routePath.split('/');
    // console.log(split);
    //
    // if (split.indexOf("farms") > 0) {
    //   this.rootPage = FarmsComponent;  // Dev Start route for http://localhost:8100/#/dev
    // } else {
    //   this.rootPage = CowsComponent;   // default start page
    // }

    this.initializeApp();

    // used for an example of ngFor and navigation
    this.pages = [
      {title: 'Cows', component: CowsComponent, icon: 'paw' },
      {title: 'Farms', component: FarmsComponent, icon: 'home'}
    ];

  }


  initializeApp() {
    this.platform.ready().then(() => {

      Deeplinks.routeWithNavController(this.nav, {
        '/cows/:cowId': CowComponent,
      }).subscribe((match) => {
        // match.$route - the route we matched, which is the matched entry from the arguments to route()
        // match.$args - the args passed in the link
        // match.$link - the full link data
        console.log('Successfully matched route', match);
      }, (nomatch) => {
        // nomatch.$link - the full link data
        console.error('Got a deeplink that didn\'t match', nomatch);
      });

      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();
    });
  }

  openPage(page) {
    console.log(page);
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}




