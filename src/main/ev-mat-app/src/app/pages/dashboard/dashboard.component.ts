import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Clerk} from '@clerk/clerk-js';
import {environment} from '../../../environment';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements  OnInit {
  username: string | null = null;
  secureData: string | null = null;
  // @ts-ignore
  userEmail: EmailAddressResource |  null;
  password: string | null = null;

  constructor(private http: HttpClient) {}

  async ngOnInit() {
    const clerk = new Clerk(environment.clerkPublishableKey);
    await clerk.load();

    const user = clerk.user;

    if (user) {
      this.username = user.username || user.firstName || user.fullName || 'friend';
      console.log(this.username);
      this.userEmail = user.primaryEmailAddress;
      console.log(this.userEmail);
      const token = await clerk.session?.getToken();
      console.log('JWT:', token);

    }
  }
}





