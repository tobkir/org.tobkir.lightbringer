import {importProvidersFrom} from '@angular/core';

import {AppComponent} from './app/app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule, provideAnimations} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app/app-routing.module';
import {bootstrapApplication, BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';


bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(BrowserModule, BrowserAnimationsModule, AppRoutingModule, FormsModule, ReactiveFormsModule),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimations(), provideAnimationsAsync(), provideCharts(withDefaultRegisterables())
  ]
})
  .catch(err => console.error(err));
