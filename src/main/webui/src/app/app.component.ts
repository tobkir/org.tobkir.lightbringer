import {Component} from '@angular/core';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {NavMenuComponent} from "./components/nav-menu/nav-menu.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatListModule,
    NavMenuComponent
  ],
  styleUrls: ['./app.component.scss'],
  standalone: true
})
export class AppComponent {
  title = 'lightbringer';
  opened: boolean = false;
}
