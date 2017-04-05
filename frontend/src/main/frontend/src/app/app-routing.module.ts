import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './services/auth-guard.service';
import { LoginComponent } from './components/login.component';
import { HomeComponent } from './components/home.component';
import { HeroesComponent } from './components/heroes.component';
import { WelcomeComponent } from './components/welcome.component';
import { HeroComponent } from './components/hero.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard], children: [
    { path: '', component: WelcomeComponent },
    { path: 'heroes', component: HeroesComponent },
    { path: 'hero/:id', component: HeroComponent }
  ] },
  { path: 'login', component: LoginComponent },
  { path: 'error', redirectTo: ''}
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
