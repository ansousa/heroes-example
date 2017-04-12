import 'rxjs/add/operator/switchMap';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Hero } from '../models/hero';
import { Image } from '../models/image';
import { MessageComponent } from './message.component';
import { Message, MessageType } from '../models/message';
import { HeroService } from '../services/hero.service';
import { ModalComponent } from './modal.component';

@Component({
  selector: 'hero',
  templateUrl: './html/hero.component.html',
  styleUrls: ['./css/hero.component.css']
})
export class HeroComponent implements OnInit{
  @Input() hero: Hero = new Hero();
  action: string = 'create';
  @ViewChild(MessageComponent) message: MessageComponent;
  noHeroImagePath: String = "assets/img/noHeroImg.png";
  heroImage: String;
  @ViewChild(ModalComponent) modal: ModalComponent;
  imageFile: string;

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute,
    private router: Router){}

  ngOnInit(): void {
    this.heroImage = this.noHeroImagePath;
    this.route.params.switchMap((params: Params) => {
      if(params['id'] != undefined){
        this.action = 'update';
        return this.heroService.getHero(+params['id']);
      }
      else return new Promise(resolve =>{
        return new Hero();
      });
    }).subscribe(hero => {
      this.route.queryParams.subscribe((params: Params) => {
        if(params["created"])
          this.message.add(new Message(MessageType.success, 'Hero ' + params["created"]  + ' created successfully.'));
      });
      this.hero = hero;
      this.loadImage();
    });
    //return this.hero = hero;
  }

  save(): void {
    this.heroService.updateHero(this.hero)
      .then(hero => {
        this.message.add(new Message(MessageType.success, 'Hero updated successfully.'));
        return this.hero = hero;
      });
  }

  create(): void {
    this.heroService.createHero(this.hero)
      .then(hero => {
        this.router.navigate(["hero", hero.id], {queryParams: {created: hero.name}});
      });
  }

  deleteImage(): void {
    this.heroService.deleteHeroImg(this.hero)
      .then(() => {
          this.heroImage = this.noHeroImagePath;
          this.message.add(new Message(MessageType.success, 'Hero\'s image deleted successfully.'));
      })
  }

  changeImageFile(event: Event): void{
    let inputValue: any = event.target;
    let file: File = inputValue.files[0];
    let reader: FileReader = new FileReader();
    let data;
    reader.onloadend = (e) => {
      data = reader.result;
    }
    reader.readAsArrayBuffer(file);
    console.log(inputValue, data);
  }

  addImage(): void {
    console.log(this.imageFile);
    this.heroService.addHeroImg(this.hero, null);
  }

  private loadImage(): void {
    this.heroService.getHeroImg(this.hero).then(data => this.heroImage = data);
  }
}
