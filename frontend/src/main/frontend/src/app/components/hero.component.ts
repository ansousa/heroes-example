import 'rxjs/add/operator/switchMap';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Hero } from '../models/hero';
import { MessageComponent } from './message.component';
import { Message, MessageType } from '../models/message';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'hero',
  templateUrl: './html/hero.component.html',
  styleUrls: ['./css/hero.component.css']
})
export class HeroComponent implements OnInit{
  @Input() hero: Hero = new Hero();
  action: string = 'create';
  @ViewChild(MessageComponent) message: MessageComponent;

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute,
    private router: Router){}

  ngOnInit(): void {
    this.route.params.switchMap((params: Params) => {
      if(params['id'] != undefined){
        this.action = 'update';
        return this.heroService.getHero(+params['id']);
      }
      else return new Promise(resolve => new Hero());
    }).subscribe(hero => this.hero = hero);
  }

  save(): void {
    this.heroService.updateHero(this.hero)
      .then(hero => {
        this.message.add(new Message(MessageType.success, 'Hero updated successfully.'))
        return this.hero = hero;
      });
  }

  create(): void {

  }
}
