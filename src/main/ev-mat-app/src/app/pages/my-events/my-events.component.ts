import {Component, ViewChild} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {SideNavComponent} from '../../components/side-nav/side-nav.component';
import {MatSidenav} from '@angular/material/sidenav';
import {SidenavService} from '../../services/sidenav.service';

@Component({
  selector: 'app-my-events',
  imports: [
    RouterOutlet, SideNavComponent
  ],
  templateUrl: './my-events.component.html',
  styleUrl: './my-events.component.scss'
})
export class MyEventsComponent {
  title = 'My Events';
  @ViewChild('sidenav') sidenav!: MatSidenav;

  constructor(private sidenavService: SidenavService) {}

  ngAfterViewInit(): void {
    this.sidenavService.setSidenav(this.sidenav);
  }

  ngOnDestroy(): void {
    this.sidenavService.clearSidenav();
  }


}
