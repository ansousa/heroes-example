import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

class Route {
  parts: {part: string, full: string}[];

}

@Component({
  selector: 'path',
  templateUrl: './html/path.component.html',
  styleUrls: ['./css/path.component.css']
})
export class PathComponent implements OnInit {
  route: Route;
  constructor(private router: Router){}

  ngOnInit(): void {
    this.route = new Route();
    this.router.events.subscribe((event) => {
      if(event instanceof NavigationEnd){
        this.route.parts = [];
        if(event.url != "/"){
          let split = event.url.split("/");
          for(let i = 0; i < split.length; i++){
            let full = "";
            if(i > 0)
              full = this.route.parts[i-1].full;
            this.route.parts.push({part: split[i], full: full + "/" + split[i]});
          }
          this.route.parts.shift();
        }
      }
    });
  }
}
