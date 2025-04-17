import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {MAT_DATE_LOCALE, provideNativeDateAdapter} from '@angular/material/core';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {provideHttpClient, withFetch, withJsonpSupport} from '@angular/common/http';
import {provideMomentDateAdapter} from '@angular/material-moment-adapter';


export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    {provide: MAT_DATE_LOCALE, useValue: 'en-US'},
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}},
    provideRouter(routes),
    provideHttpClient(withFetch(), withJsonpSupport()),
    provideNativeDateAdapter(),
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
  ]
};
