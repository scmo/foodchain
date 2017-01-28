import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the CowService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class CowService {
  constructor(public http: Http) {
    console.log('Hello CowService Provider');
  }

  load() {
    // don't have the data yet
    return new Promise(resolve => {
      // We're using Angular HTTP provider to request the data,
      // then on the response, it'll map the JSON data to a parsed JS object.
      // Next, we process the data and resolve the promise with the new data.
      this.http.get('https://jsonplaceholder.typicode.com/users')
        .map(res => res.json())
        .subscribe(data => {
          resolve(data);
        });
    });
  }

}
