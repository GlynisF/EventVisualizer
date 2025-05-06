import {ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, OnInit, ViewChild} from '@angular/core';
import {NgFor, NgIf} from '@angular/common';
import {expandCollapse, fadeIn, fadeInOut, rotateToggle} from '../../util/animations';
import {HttpClientService} from '../../services/http-client.service';
import {Event, Notebook} from '../../models/entity.model';
import {CdkAccordion, CdkAccordionItem} from '@angular/cdk/accordion';
import {NoteCardComponent} from '../cards/note-card/note-card.component';
import {ReflectionCardComponent} from '../cards/reflection-card/reflection-card.component';
import {MaterialCompsModule} from '../../materialcomps/materialcomps.module';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../dialog/dialog.component';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DetailComponent} from '../forms/detail/detail.component';
import {
  buildDetailArray,
  buildGoalForm,
  buildLocationForm,
  buildNoteForm,
  buildPerformerArray,
  buildReflectionForm
} from '../../util/form-util';
import {DisplayEventsComponent} from '../display-events/display-events.component';
import {EditEventComponent} from '../edit-event/edit-event.component';

@Component({
  animations: [rotateToggle, expandCollapse, fadeIn, fadeInOut],
  selector: 'app-side-nav',
  imports: [
    MaterialCompsModule,
    CdkAccordion,
    CdkAccordionItem,
    NgFor,
    NgIf,
    NoteCardComponent,
    ReflectionCardComponent,
    ReactiveFormsModule,
    DisplayEventsComponent,
    EditEventComponent
  ],
  templateUrl: './side-nav.component.html',
  styleUrl: './side-nav.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SideNavComponent implements OnInit {
  @ViewChild(DetailComponent) detailComponent!: DetailComponent;
  private http = inject(HttpClientService);
  private fb = inject(FormBuilder);
  private cdr = inject(ChangeDetectorRef);

  public dialog = inject(MatDialog);

  notebookData: Notebook[] = [];
  eventSelected?: Event;
  selectedNotebookId?: number;
  isOpen = false;
  createNotebook = false;
  editor = false;

  eventFormGroup!: FormGroup;
  storageForm: any = localStorage.getItem('originalEvent');

  ngOnInit() {
    this.http.getNotebooks().subscribe({
      next: (response) => {
        this.notebookData = [...response];
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error('Error fetching notebooks:', err);
      }
    });
  }

  updateEvent() {
    if (this.eventFormGroup.valid) {
      const formData = this.eventFormGroup.value;
      const eventId = formData.event.id;
      this.http.updateEvent(eventId, formData).subscribe({
        next: () => {
          console.log('Event updated successfully!');
        },
        error: (err) => {
          console.error('Error updating event:', err);
        }
      });
    }
  }

  addNotebook(): void {
    this.createNotebook = !this.createNotebook;
    this.dialog.open(DialogComponent, {
      data: { addNotebook: true }
    });
  }

  updateDialog(): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: { addNotebook: false, formGroup: this.eventFormGroup }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.updateEvent();
      }
    });
  }

  toggle(): void {
    this.isOpen = !this.isOpen;
  }

  toggleEditorMode(event: Event): void {
    if (!this.eventFormGroup) {
      this.eventFormGroup = this.fb.group({
        event: this.fb.group({
          id: [event.id],
          eventName: [event.eventName]
        }),
        performers: buildPerformerArray(this.fb, event.details?.[0]?.performers || []),
        location: buildLocationForm(this.fb),
        note: buildNoteForm(this.fb),
        goal: buildGoalForm(this.fb),
        reflection: buildReflectionForm(this.fb),
        details: buildDetailArray(this.fb, event.details || []),
      });

    }
  }

  toggleNotebook(id: number | undefined): void {
    this.selectedNotebookId = this.selectedNotebookId === id ? undefined : id;
  }

  selectedEvent(event: MouseEvent, notebookEvent: Event) {
    if (!this.editor) {
      this.eventSelected = notebookEvent;
      console.log(this.eventSelected);
    }
    if (this.editor) {
      event.stopPropagation();
      if (this.eventFormGroup.valid) {
        const formData = this.eventFormGroup.value;
        console.log('parent formGroup:', this.eventFormGroup.get('goal')?.value);

        console.log(formData);
      }
      this.updateDialog();
    }
  }

  getDetailGroup(): FormGroup {
    return this.eventFormGroup.get('details') as FormGroup;
  }

  getEventContent(): any[] {
    const output: any[] = [];

    if (!this.eventSelected) return output;

    this.eventSelected.details?.forEach((detail) => {
      output.push({
        type: 'detail',
        data: detail,
        locations: detail.locations || []
      });

      detail.performers?.forEach((performer) => {
        output.push({
          type: 'performer',
          data: performer
        });
      });
    });

    if (this.eventSelected.note) {
      output.push({ type: 'note', data: this.eventSelected.note });
    }

    if (this.eventSelected.goal) {
      output.push({ type: 'goal', data: this.eventSelected.goal });
    }

    if (this.eventSelected.reflection) {
      output.push({ type: 'reflection', data: this.eventSelected.reflection });
    }

    return output;
  }

  convertTimeStringToDate(timeString: string): Date {
    const [hours, minutes, seconds] = timeString.split(':').map(Number);
    const date = new Date();
    date.setHours(hours, minutes, seconds || 0);
    return date;
  }

  trackById(index: number, item: any): number {
    return item.id;
  }

  trackByNotebookId(index: number, notebook: Notebook): number {
    return notebook.id!;
  }

  trackByEventId(index: number, event: Event): number {
    return event.id!;
  }
}
