import {Component, inject, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {AsyncPipe, NgOptimizedImage} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {RouterLink, RouterOutlet} from "@angular/router";
import {MatMenu, MatMenuTrigger} from "@angular/material/menu";
import {GeneralInfoService} from "../../services/logic/general-info.service";
import {GeneralInfoContainer} from "../../model/general-info-container.model";
import {MatGridList, MatGridTile} from "@angular/material/grid-list";

@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrl: './nav-menu.component.scss',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    AsyncPipe,
    RouterOutlet,
    RouterLink,
    MatMenuTrigger,
    MatMenu,
    NgOptimizedImage,
    MatGridList,
    MatGridTile,

  ]
})
export class NavMenuComponent implements OnInit {
  ip: GeneralInfoContainer = new GeneralInfoContainer("");
  private breakpointObserver = inject(BreakpointObserver);

  constructor(
    private generalInfoService: GeneralInfoService
  ) {
  }

  ngOnInit(): void {
    this.getIp();
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  private getIp() {
    setTimeout(() => {

    })
    this.generalInfoService.getIp().subscribe(
      ip => this.ip = ip
    )
  }
}
