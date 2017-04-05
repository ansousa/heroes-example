import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthModule } from './auth.module';

import { AppRoutingModule} from './app-routing.module';
import { AuthService} from './services/auth.service';
import { AuthGuard } from './services//auth-guard.service';
import { HeroService} from './services/hero.service';

import { AppComponent } from './components/app.component';
import { LoginComponent } from './components/login.component';
import { HomeComponent } from './components/home.component';
import { HeroesComponent } from './components/heroes.component';
import { WelcomeComponent } from './components/welcome.component';
import { PathComponent } from './components/path.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeroesComponent,
    WelcomeComponent,
    PathComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    AuthModule
  ],
  providers: [
    AuthService,
    AuthGuard,
    HeroService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }