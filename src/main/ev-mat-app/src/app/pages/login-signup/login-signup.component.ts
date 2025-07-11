import {Component} from '@angular/core';
import {AuthDialogComponent} from '../../auth-dialog/auth-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-login-signup',
  imports: [],
  templateUrl: './login-signup.component.html',
  styleUrl: './login-signup.component.scss'
})
export class LoginSignupComponent {
  constructor(private dialog: MatDialog) {}

  openAuth(mode: 'sign-in' | 'sign-up') {
    this.dialog.open(AuthDialogComponent, {
      width: '400px',
      data: { mode },
    });
  }

}
