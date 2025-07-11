import {bootstrapApplication} from '@angular/platform-browser';
import {appConfig} from './app/app.config';
import {AppComponent} from './app/app.component';
import {BrowserAnimationsModule, provideAnimations} from '@angular/platform-browser/animations';
import {importProvidersFrom} from '@angular/core';
import {FullCalendarModule} from '@fullcalendar/angular';


bootstrapApplication(AppComponent,  {
  ...appConfig,
  providers: [
    ...appConfig.providers,
    importProvidersFrom(BrowserAnimationsModule), provideAnimations(),FullCalendarModule,
  ],
}).catch((err) => console.error(err));
