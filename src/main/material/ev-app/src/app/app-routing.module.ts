import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent, title: 'Event Visualizer Homepage'},
  {path: 'event-plan', title: 'Event Plan', loadComponent: () => import('./components/plan-event/plan-event.component').then(m => m.PlanEventComponent)},
  {path: '', pathMatch: 'full', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
