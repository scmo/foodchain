import {NgModule, ErrorHandler} from "@angular/core";
import {IonicApp, IonicModule, IonicErrorHandler} from "ionic-angular";
import {MyApp} from "./app.component";
import {CowsComponent} from "../pages/cows/cows";
import {CowComponent} from "../pages/cows/detail/cow";
import {FarmsComponent} from "../pages/farms/farms";
import {FarmComponent} from "../pages/farms/detail/farm";
import {MomentModule} from "angular2-moment";

@NgModule({
  declarations: [
    MyApp,
    CowsComponent,
    CowComponent,
    FarmsComponent,
    FarmComponent
  ],
  imports: [
    IonicModule.forRoot(MyApp),
    MomentModule
  ],
  bootstrap: [IonicApp],

  entryComponents: [
    MyApp,
    CowsComponent,
    CowComponent,
    FarmsComponent,
    FarmComponent
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}]
})
export class AppModule {
}
