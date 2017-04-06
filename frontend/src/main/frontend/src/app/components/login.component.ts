import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

import { MessageComponent } from './message.component';
import { Message, MessageType } from '../models/message';

@Component({
  selector: 'login',
  templateUrl: './html/login.component.html',
  styleUrls: ['./css/login.component.css']
})

export class LoginComponent {
  name: string;
  password: string;
  @ViewChild(MessageComponent) message: MessageComponent;

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.auth.login(this.name, this.password)
    .subscribe(
      data => {
        localStorage.setItem('token', data.token);
        this.router.navigate(['']);
      },
      error => this.message.add(new Message(MessageType.error, "Invalid name or password."))
    );
  }
}
