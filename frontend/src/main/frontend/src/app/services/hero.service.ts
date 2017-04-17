import { Injectable }    from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Hero } from '../models/hero';
import { Image } from '../models/image';

@Injectable()
export class HeroService {

  private heroesUrl = 'api/heroes';
  private heroUrl = 'api/hero';
  private heroImgUrl = 'api/hero/img';

  constructor(private authHttp: AuthHttp) { }

  getHeroes(): Promise<Hero[]> {
    return this.authHttp.get(this.heroesUrl)
      .toPromise()
      .then(response => response.json() as Hero[])
      .catch(this.handleError);
  }

  getHero(id: number): Promise<Hero> {
    return this.authHttp.get(this.heroUrl + "/" + id)
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(this.handleError);
  }

  updateHero(hero: Hero): Promise<Hero> {
    return this.authHttp.put(this.heroUrl, hero)
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(this.handleError);
  }

  createHero(hero: Hero): Promise<Hero> {
    return this.authHttp.post(this.heroUrl, hero)
      .toPromise()
      .then(response => response.json() as Hero)
      .catch(this.handleError);
  }

  deleteHero(id: number): Promise<Boolean> {
    return this.authHttp.delete(this.heroUrl + "/" + id)
      .toPromise()
      .then(response => true)
      .catch(this.handleError);
  }

  getHeroImg(hero: Hero): Promise<String> {
    let url: string = this.heroImgUrl + "/" + hero.id;
    return this.authHttp.get(url)
      .toPromise()
      .then(response => response.text());
  }

  deleteHeroImg(hero: Hero): Promise<Boolean> {
    let url: string = this.heroImgUrl + "/" + hero.id;
    return this.authHttp.delete(url)
      .toPromise()
      .then(response => true)
      .catch(this.handleError);
  }

  addHeroImg(hero: Hero, image: Image){
    let url: string = this.heroImgUrl + "/" + hero.id;
    return this.authHttp.put(url, image)
      .toPromise()
      .then(response => true)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
