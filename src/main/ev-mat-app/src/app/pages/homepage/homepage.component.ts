import {Component, OnInit} from '@angular/core';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';
import {MatDialogModule} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardComponent} from '../dashboard/dashboard.component';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-homepage',
  imports: [
    MaterialComponents,
    MatDialogModule,
    DashboardComponent,
    CommonModule
  ],
  templateUrl: './homepage.component.html',
  styles: `
    .staatliches-regular {
    font-family: "Staatliches", sans-serif;
    font-weight: 400;
    font-style: normal;
  }
  `
})
export class HomepageComponent implements OnInit  {

  showUserInfo = false;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    let toolbar = document.getElementById('nav-toolbar');
    toolbar?.remove();

    const queryParams = this.route.snapshot.queryParams;

    this.showUserInfo = Object.keys(queryParams).some(key =>
      key.startsWith('__clerk')
    );

    if (this.showUserInfo) {
      // Optional: clean the URL by removing Clerk query params
      this.router.navigate([], {
        queryParams: {},
        replaceUrl: true,
        relativeTo: this.route
      });
    }
  }


  redirectToClerk(mode: 'sign-in' | 'sign-up') {
    const base = mode === 'sign-in' ? 'sign-in' : 'sign-up';
    const redirectUrl = encodeURIComponent('http://localhost:4200/dashboard');
    window.location.href = `https://honest-gobbler-26.accounts.dev/sign-in?redirect_url=${redirectUrl}`;

  }





}
