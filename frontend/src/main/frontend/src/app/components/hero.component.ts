import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Hero } from '../models/hero';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'hero',
  templateUrl: './html/hero.component.html',
  styleUrls: ['./css/hero.component.css']
})
export class HeroComponent implements OnInit{
  hero: Hero;

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.params.switchMap((params: Params) => this.heroService.getHero(+params['id']))
    .subscribe(hero => this.hero = hero);
  }
}
