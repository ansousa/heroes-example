import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/map';
import { JwtHelper } from 'angular2-jwt';

@Injectable()
export class AuthService {
  private jwtHelper: JwtHelper = new JwtHelper()

  constructor(private http: Http, private router: Router, private location: Location) {}

  login(name: string, password: string) {
    return this.http.post('api/auth/login', {name: name, password: password})
      .map(res => res.json());
  }

  loggedIn() {
    const token: string = localStorage.getItem('token');
    const ret = token == null ? false : !this.jwtHelper.isTokenExpired(token);
    return ret;
  }

  logout() {
    localStorage.removeItem('token');
    this.location.replaceState('/');
    this.router.navigate(['login']);
  }

}
