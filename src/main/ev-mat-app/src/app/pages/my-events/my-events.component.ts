import {Component} from '@angular/core';
import {SideNavComponent} from '../../components/side-nav/side-nav.component';
import {MatGridListModule} from '@angular/material/grid-list';

@Component({
  selector: 'app-my-events',
  imports: [
    SideNavComponent, MatGridListModule
  ],
  templateUrl: './my-events.component.html',
  styleUrl: './my-events.component.scss'
})
export class MyEventsComponent {
  title = 'My Events';



}
