import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'home',
  templateUrl: './html/home.component.html',
  styleUrls: ['./css/home.component.css']
})

export class HomeComponent {
  route: string;

  constructor(private auth: AuthService){}

  loggedIn(){
    return this.auth.loggedIn();
  }

  logout(){
    this.auth.logout();
  }
}
