import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/map";

/*
  Generated class for the CowService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class CowService {
  endpoint: string = 'http://foodchain.ch/cow/';
  //endpoint: string = 'http://192.168.43.197:8090/cow/';
  constructor(public http: Http) {
    console.log('Hello CowService Provider');
  }

  load() {
    // don't have the data yet
    return new Promise(resolve => {
      // We're using Angular HTTP provider to request the data,
      // then on the response, it'll map the JSON data to a parsed JS object.
      // Next, we process the data and resolve the promise with the new data.
      this.http.get(this.endpoint)
        .map(res => res.json())
        .subscribe(data => {
          resolve(data);
        });
    });
  }

  loadCow(id:string){

    return new Promise(resolve => {
      // We're using Angular HTTP provider to request the data,
      // then on the response, it'll map the JSON data to a parsed JS object.
      // Next, we process the data and resolve the promise with the new data.
      this.http.get(this.endpoint+id+'/')
        .map(res => res.json())
        .subscribe(data => {
          console.log('data');
          console.log(data);
          resolve(data);
        });
    });
  }

}