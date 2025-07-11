import {Component, OnDestroy, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {Subscription} from 'rxjs';
import {NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';


@Component({
  selector: 'app-nav-toolbar',
  imports: [CommonModule, MatToolbarModule, MatIconModule, MatTabsModule, RouterLink, RouterOutlet, MaterialComponents, RouterLinkActive],
  templateUrl: './nav-toolbar.component.html',
  styleUrl: './nav-toolbar.component.scss'
})
export class NavToolbarComponent implements OnInit, OnDestroy {
  private routerSubscription: Subscription | undefined;
  activeTab: string = '';

  icons = ['home', 'event_list', 'pages']

  links = [
    { label: 'Home', route: '/home' },
    { label: 'My Events', route: '/my-events'},
    { label: 'Planner', route: '/plan-event' },

  ];
  trackByLabel(index: number, item: any): string {
    return item.label;
  }

  activeLink = this.links[0];

  constructor(private router: Router) {}

  ngOnInit() {
    // Subscribe to router events
    this.routerSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.setActiveTabFromUrl(event.urlAfterRedirects);
      }
    });
  }

  ngOnDestroy() {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  setActiveTabFromUrl(url: string) {
    if (url.includes('home')) {
      this.activeTab = 'Home';
    } else if (url.includes('about')) {
      this.activeTab = 'About';
    } else {
      this.activeTab = '';
    }
  }

}
