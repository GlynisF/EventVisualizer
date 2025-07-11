import {Component} from '@angular/core';
import {EventCalendarComponent} from '../../components/event-calendar/event-calendar.component';

@Component({
  selector: 'app-calendar',
  imports: [
    EventCalendarComponent
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.scss'
})
export class CalendarComponent {

}
