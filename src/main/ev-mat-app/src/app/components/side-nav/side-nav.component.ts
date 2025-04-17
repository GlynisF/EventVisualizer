import {Component, inject, Input, OnInit} from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {expandCollapse, fadeIn, rotateToggle} from '../../util/animations';
import {HttpClientService} from '../../services/http-client.service';
import {CommonModule, NgForOf, NgIf} from '@angular/common';
import {Detail, Event, Goal, Location, Note, Notebook, Performer, Reflection} from '../../models/entity.model';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';

@Component({
  animations: [rotateToggle, expandCollapse, fadeIn],
  selector: 'app-side-nav',
  imports: [MatSidenavModule, MatListModule, MatIconModule, MatIconModule, NgForOf, MatGridListModule, MatCardModule, NgIf, CommonModule, MatButtonModule],
  templateUrl: './side-nav.component.html',
  styleUrl: './side-nav.component.scss'
})
export class SideNavComponent implements OnInit {
  menuOpen = false;
  private _notebooks: Notebook[] = [];
  http = inject(HttpClientService);
  notebookData: Notebook[] = [];
  selectedNotebook?: Notebook;
  events: Event[] = [];

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
  }

  ngOnInit() {
    this.http.getNotebooks().subscribe({
      next: (response) => {
        this._notebooks.push(...response);
        this.notebookData = [...response];
        console.log(response);
        console.log(this._notebooks);
      },
      error: (err) => {
        console.error('Error fetching notebooks:', err);
      },
      complete: () => {
        return this._notebooks;
      }
    });

  }

  @Input()
  set notebooks(value: Notebook[]) {
    this._notebooks = value;
  }
  get notebooks(): Notebook[] {
    return this._notebooks;
  }

  selectedEventDisplay?: {
    event: Event;
    detail?: Detail;
    performer?: Performer;
    location?: Location;
    goal?: Goal;
    note?: Note;
    reflection?: Reflection;
  };

  selectEvent(event: any): void {
    const detail = event.details?.[0];
    const performer = detail?.performers?.[0];
    const location = detail?.locations?.[0];

    this.selectedEventDisplay = {
      event,
      detail: {
        ...detail,
        dateOfEvent: detail.dateOfEvent ? new Date(detail.dateOfEvent) : undefined,
        startTime: detail.startTime ? new Date(`1970-01-01T${detail.startTime}`) : undefined,
        endTime: detail.endTime ? new Date(`1970-01-01T${detail.endTime}`) : undefined,
      },
      performer,
      location,
      goal: event.goal,
      note: event.note,
      reflection: event.reflection
    };
  }

  formatDate(date: string): string {
    return new Date(date).toLocaleDateString();
  }

  activeNotebookIndex: number | null = null;

  toggleNotebook(index: number): void {
    this.activeNotebookIndex = this.activeNotebookIndex === index ? null : index;
  }


}
