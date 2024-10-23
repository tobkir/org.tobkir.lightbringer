import {importProvidersFrom} from '@angular/core';

import {AppComponent} from './app/app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule, provideAnimations} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app/app-routing.module';
import {bootstrapApplication, BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {NgxChartsModule} from "@swimlane/ngx-charts";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';


bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(BrowserModule, BrowserAnimationsModule, AppRoutingModule, FormsModule, ReactiveFormsModule, NgxChartsModule),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimations(), provideAnimationsAsync()
  ]
})
  .catch(err => console.error(err));
