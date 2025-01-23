import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
import {MatButton} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {NavMenuComponent} from "./components/nav-menu/nav-menu.component";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    imports: [
        RouterOutlet,
        RouterLink,
        RouterLinkActive,
        MatMenu,
        MatMenuItem,
        MatButton,
        MatMenuTrigger,
        MatSidenavModule,
        MatIconModule,
        MatToolbarModule,
        MatListModule,
        NavMenuComponent
    ],
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'lightbringer';
  opened: boolean = false;
}
