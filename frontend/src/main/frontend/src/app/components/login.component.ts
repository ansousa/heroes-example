import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './html/login.component.html',
  styleUrls: ['./css/login.component.css']
})

export class LoginComponent {
  name: string;
  password: string;
  error: Boolean = false;

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.auth.login(this.name, this.password)
    .subscribe(
      data => {
        localStorage.setItem('token', data.token);
        this.router.navigate(['']);
      },
      error => this.error = true
    );
  }

  hideError(){
    this.error = false;
  }
}
