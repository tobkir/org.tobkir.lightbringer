import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {BatteryComponent} from "./components/battery/battery.component";
import {PvComponent} from "./components/pv/pv.component";
import {ConsumptionComponent} from "./components/consumption/consumption.component";

const routes: Routes = [
  {path:"", component: HomeComponent},
  {path:"home", component: HomeComponent},
  {path:"battery", component: BatteryComponent},
  {path:"pv", component: PvComponent},
  {path:"consumption", component: ConsumptionComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
