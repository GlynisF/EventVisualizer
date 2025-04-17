import {Routes} from '@angular/router';

export const routes: Routes = [
  {path: 'home', loadComponent:() => import('./pages/homepage/homepage.component').then(c => c.HomepageComponent)},
  {path: 'plan-event', loadComponent:() => import('./pages/plan-event/plan-event.component').then(c => c.PlanEventComponent)},
  {path: 'my-events', loadComponent:() => import('./pages/my-events/my-events.component').then(c => c.MyEventsComponent)},
  {path: '', pathMatch: 'full', redirectTo: 'home'}
];
