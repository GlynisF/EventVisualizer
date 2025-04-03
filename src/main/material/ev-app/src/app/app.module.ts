import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavToolbarComponent} from './components/nav-toolbar/nav-toolbar.component';

import {MaterialCompsModule} from './materialcomps/materialcomps.module';
import {PlanEventComponent} from './components/plan-event/plan-event.component';
import {ReactiveFormsModule} from '@angular/forms';
import {FormComponent} from './form/form.component';
import {MAT_DATE_LOCALE, provideNativeDateAdapter} from '@angular/material/core';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {provideMomentDateAdapter} from '@angular/material-moment-adapter';


export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/DD/YYYY',
    timeInput: 'hh:mm a z'
  },
  display: {
    dateInput: 'ddd MMM, D YYYY',
    timeOptionLabel: 'HH:mm A',
    timeInput: 'HH:mm A',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialCompsModule,
    NavToolbarComponent,
    PlanEventComponent,
    ReactiveFormsModule
  ],
  providers: [
    provideMomentDateAdapter({
      parse: {
        dateInput: 'MM/DD/YYYY',
        timeInput: 'hh:mm:ss a z'
      },
      display: {
        dateInput: 'ddd MMM D, YYYY',
        timeOptionLabel: 'HH:mm A',
        timeInput: 'HH:mm A',
        monthYearLabel: 'MMM YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'MMMM YYYY',
      },
    }),
    {provide: MAT_DATE_LOCALE, useValue: 'en-US'},
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}},
    provideNativeDateAdapter(),

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
