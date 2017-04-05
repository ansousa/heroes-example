import { Injectable }    from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import 'rxjs/add/operator/toPromise';

import { Hero } from '../models/hero';

@Injectable()
export class HeroService {

  private heroesUrl = 'api/heroes';

  constructor(private authHttp: AuthHttp) { }

  getHeroes(): Promise<Hero[]> {
    return this.authHttp.get(this.heroesUrl)
      .toPromise()
      .then(response => {
          console.log(response);
          return response.json() as Hero[]
      })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
