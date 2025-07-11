import {Routes} from '@angular/router';

export const routes: Routes = [
  {path: 'home', title: 'Event Planner Home', loadComponent:() => import('./pages/homepage/homepage.component').then(c => c.HomepageComponent)},
  {path: 'plan-event', title: 'Plan Event', loadComponent:() => import('./pages/plan-event/plan-event.component').then(c => c.PlanEventComponent)},
  {path: 'my-events', title: 'My-Events', loadComponent:() => import('./pages/my-events/my-events.component').then(c => c.MyEventsComponent)},
  {path: 'dashboard', title:'User Dashboard', loadComponent:() => import('./pages/dashboard/dashboard.component').then(c => c.DashboardComponent)},
  {path: 'calendar', title:'Events Calendar', loadComponent:() => import('./pages/calendar/calendar.component').then(c => c.CalendarComponent)},
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: '*', redirectTo: 'home'}
];
