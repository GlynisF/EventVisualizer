import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {Subscription} from 'rxjs';
import {NavigationEnd, Router, RouterLink} from '@angular/router';


@Component({
  selector: 'app-nav-toolbar',
  imports: [CommonModule, MatToolbarModule, MatIconModule, MatTabsModule, RouterLink],
  templateUrl: './nav-toolbar.component.html',
  styleUrl: './nav-toolbar.component.scss'
})
export class NavToolbarComponent {
  private routerSubscription: Subscription | undefined;
  activeTab: string = ''; // Variable to track active tab

  icons = ['home', 'event_list', 'pages']

  links = [
    { label: 'Home', route: '/home' },
    { label: 'My Events', route: '/my-events'},
    { label: 'Planner', route: '/plan-event' },

  ];

  activeLink = this.links[0];

  constructor(private router: Router) {}

  ngOnInit() {
    // Subscribe to router events
    this.routerSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.setActiveTabFromUrl(event.urlAfterRedirects); // Use the final URL after redirects
      }
    });
  }

  ngOnDestroy() {
    // Unsubscribe when the component is destroyed to avoid memory leaks
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
      this.activeTab = ''; // Default value if no match
    }
  }

}
