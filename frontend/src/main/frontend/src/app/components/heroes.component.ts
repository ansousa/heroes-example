import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Hero } from '../models/hero';
import { HeroService } from '../services/hero.service';
import { MessageComponent } from './message.component';
import { Message, MessageType } from '../models/message';
import { ModalComponent } from './modal.component';

@Component({
  selector: 'heroes',
  templateUrl: './html/heroes.component.html',
  styleUrls: ['./css/heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: Hero[];
  selectedHero: Hero;
  @ViewChild(MessageComponent) message: MessageComponent;
  @ViewChild(ModalComponent) modal: ModalComponent;

  constructor(
    private router: Router,
    private heroService: HeroService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getHeroes();
    this.route.queryParams.subscribe((params: Params) => {
      if(params["deleted"])
        this.message.add(new Message(MessageType.success, 'Hero ' + params["deleted"]  + ' deleted successfully.'));
    });
  }

  getHeroes(): void {
    this.heroService.getHeroes().then(heroes => this.heroes = heroes);
  }

  warnDelete(hero: Hero){
    this.modal.show();
  }

  delete(hero: Hero){
    this.heroService.deleteHero(hero).then(() => {
      this.heroes.splice(this.heroes.indexOf(hero), 1);
      this.router.navigate(["heroes"], {queryParams: {deleted: hero.name}});
    });
  }
}
