import {AfterViewInit, ChangeDetectionStrategy, Component} from '@angular/core';
import {Clerk} from '@clerk/clerk-js';

declare global {
  interface Window {
    Clerk?: Clerk | undefined;
  }
}

@Component({
  selector: 'app-auth',
  imports: [],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthComponent  implements AfterViewInit {
  ngAfterViewInit() {
  }


}
