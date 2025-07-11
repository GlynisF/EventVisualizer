import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-callback',
  imports: [],
  templateUrl: './callback.component.html',
  styleUrl: './callback.component.scss'
})
export class CallbackComponent implements OnInit {
  constructor(private router: Router, private http: HttpClient) {}

  async ngOnInit() {
    // fetch user info from your Next.js Clerk API
    const user = await this.http.get('/api/me').toPromise();

    // optionally store user info in localStorage, etc.

    this.router.navigate(['/my-events']);
  }
}
