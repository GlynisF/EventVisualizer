import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {FullCalendarComponent, FullCalendarModule} from '@fullcalendar/angular';
import {CommonModule} from '@angular/common';
import {Calendar, CalendarOptions, EventInput} from '@fullcalendar/core';
import multiMonthPlugin from '@fullcalendar/multimonth';
import dayGridPlugin from '@fullcalendar/daygrid';
import bootstrap5Plugin from '@fullcalendar/bootstrap5';
import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import {HttpClientService} from '../../services/http-client.service';
import {Notebook} from '../../models/entity.model';
import moment from 'moment';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';


@Component({
  selector: 'app-event-calendar',
  imports: [CommonModule, FullCalendarModule, MaterialComponents],
  templateUrl: './event-calendar.component.html',
  styleUrls: ['./event-calendar.component.scss'],
})
export class EventCalendarComponent implements OnInit {
  private http = inject(HttpClientService);
  @ViewChild('calendarRef') calendarComponent!: FullCalendarComponent;
  calendarOptions: CalendarOptions = {
    plugins: [multiMonthPlugin, dayGridPlugin, bootstrap5Plugin, listPlugin, timeGridPlugin],
    themeSystem: 'bootstrap5',
    displayEventEnd: true,
    displayEventTime: true,
    timeZone: 'UTC',
    initialView: 'timeGridWeek',
    height: 800,
    headerToolbar: false,
    events: [],
  };


  calendarTitleView: string = '';
  get calendarApi(): Calendar {
    return this.calendarComponent.getApi();
  }

  ngAfterViewInit(): void {
    console.log(this.calendarApi.getEvents());
  }

  calendarPrev() {
    this.calendarComponent.getApi().prev();
    this.calendarComponent.getApi();
    this.updateCalendarTitle()
  }
  calendarNext() {
    this.calendarComponent.getApi().next();
    this.updateCalendarTitle();

  }
  calendarToday() {
    this.calendarComponent.getApi().today();
    this.updateCalendarTitle();
  }
  changeView(view: string) {
    this.calendarComponent.getApi().changeView(view);
    this.updateCalendarTitle();
  }


  ngOnInit() {
    this.http.getNotebooks().subscribe((notebooks: Notebook[]) => {
      // @ts-ignore
      const allEvents: EventInput[] = notebooks.flatMap(notebook =>
        notebook.events?.map(event => {
          const detail = event.details?.[0];

          const startDate = detail?.dateOfEvent;
          const startTime = detail?.startTime;
          const endTime = detail?.endTime;
          console.log(endTime);


          const start = startDate && startTime
            ? moment(`${startDate}T${startTime}`, 'YYYY-MM-DD THH:mm:ss').toISOString()
            : undefined;

          const end = startDate && endTime
            ? moment(`${startDate}T${endTime}`, 'YYYY-MM-DD THH:mm:ss').toISOString()
            : undefined;

          return {
            id: event.id,
            title: `${event.eventName}`,
            start: start,
            end: end,
            allDay: false,
            extendedProps: {
              notebookId: notebook.id,
              eventId: event.id
            }
          };
        })

      );
      this.calendarOptions = {
        ...this.calendarOptions,
        events: allEvents,
        eventBackgroundColor: '#d3d93b',
        eventTextColor: '#000000',
        eventBorderColor: '#000000',
        eventDisplay: 'block',
        allDaySlot: false,
        defaultAllDay: false,
        forceEventDuration: true,
        navLinks: false,
        nowIndicator: true,

      };
    });
  }

  updateCalendarTitle() {
    const currentDate = this.calendarApi.getDate();
    const viewType = this.calendarApi.view.type;

    switch (viewType) {
      case 'dayGridMonth':
        this.calendarTitleView = currentDate.toLocaleString('default', {
          month: 'long',
          year: 'numeric'
        });
        break;

      case 'dayGridWeek':
      case 'timeGridWeek':
      case 'listWeek': {
        const start = this.calendarApi.view.currentStart;
        const end = this.calendarApi.view.currentEnd;
        this.calendarTitleView =
          start.toLocaleDateString('default', { month: 'short', day: 'numeric' }) +
          ' – ' +
          end.toLocaleDateString('default', { month: 'short', day: 'numeric', year: 'numeric' });
        break;
      }

      case 'dayGridDay':
      case 'listDay':
        this.calendarTitleView = currentDate.toLocaleDateString('default', {
          weekday: 'long',
          month: 'long',
          day: 'numeric',
        });
        break;

      default:
        this.calendarTitleView = '';
    }
  }


}
