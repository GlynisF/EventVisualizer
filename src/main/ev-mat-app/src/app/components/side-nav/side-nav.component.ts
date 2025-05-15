import {ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, OnInit, ViewChild} from '@angular/core';
import {NgFor, NgIf} from '@angular/common';
import {expandCollapse, fadeIn, fadeInOut, rotateToggle} from '../../util/animations';
import {HttpClientService} from '../../services/http-client.service';
import type {Event, Notebook} from '../../models/entity.model';
import {CdkAccordion, CdkAccordionItem} from '@angular/cdk/accordion';
import {MaterialCompsModule} from '../../materialcomps/materialcomps.module';
import {MatDialog} from '@angular/material/dialog';
import {DialogComponent} from '../dialog/dialog.component';
import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DetailComponent} from '../forms/detail/detail.component';
import {
  buildDetailForm,
  buildEventForm,
  buildGoalForm,
  buildLocationForm,
  buildNoteForm,
  buildPerformerForm,
  buildReflectionForm,
  normalizeTime
} from '../../util/form-util';
import {DisplayEventsComponent} from '../display-events/display-events.component';
import {EditEventComponent} from '../edit-event/edit-event.component';
import {FormService} from '../../services/form-service.service';

@Component({
  animations: [rotateToggle, expandCollapse, fadeIn, fadeInOut],
  selector: 'app-side-nav',
  imports: [
    MaterialCompsModule,
    CdkAccordion,
    CdkAccordionItem,
    NgFor,
    NgIf,
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
  helper = inject(FormService);

  originalEventValue: any;
  notebookData: Notebook[] = [];
  eventSelected?: Event;
  selectedNotebookId?: number;
  isOpen = false;
  createNotebook = false;
  editor = false;
  displayMode: 'edit' | 'display' = 'display';
  eventFormGroup!: FormGroup;
  storageForm: any = localStorage.getItem('originalEvent');

  ngOnInit() {
    this.http.getNotebooks().subscribe({
      next: (response) => {
        console.log(typeof response);
        this.notebookData = [...response];
        this.cdr.markForCheck();
        console.log(response);
      },
      error: (err) => {
        console.error('Error fetching notebooks:', err);
      }
    });
  }

  updateEvent() {
    if (this.eventFormGroup && this.eventFormGroup.valid) {
      const formData = this.eventFormGroup.value;

      const locationAddress = formData.locations.address;
      const trimmedAddress = locationAddress.trim();
      const splitAddress: any[] = trimmedAddress.split(',');

      const formattedDetail = {
        ...formData.details,
        startTime: normalizeTime(formData.details.startTime),
        endTime: normalizeTime(formData.details.endTime)
      };

      const payload = {
        event: {
          id: formData.event.id,
          eventName: formData.event.eventName,
          goal: formData.goal,
          note: formData.note,
          reflection: formData.reflection,
          details: [
            {
              ...formattedDetail,
              performers: formData.performers,
              locations: [{
                ...formData.locations,
                address: splitAddress[0].trim() ?? '',
                website: this.truncateWebsite(formData.locations.website) ?? null
              }]
            }
          ]
        }
      };

      const eventId = formData.event.id;
      this.http.updateEvent(eventId, payload).subscribe({
        next: () => {
          console.log('Event updated successfully!');
          this.originalEventValue = structuredClone(this.eventFormGroup.value);
          this.exitEditMode();
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
      } else {
        this.exitEditMode();
      }
    });
  }

  hasFormChanged(): boolean {
    if (!this.eventFormGroup || !this.originalEventValue) return false;
    return JSON.stringify(this.eventFormGroup.value) !== JSON.stringify(this.originalEventValue);
  }


  toggleEditorMode(eventObject: Event): void {
    this.editor = !this.editor;
    const performerData = eventObject.details?.[0]?.performers ?? [];
    if (!this.eventFormGroup) {
      this.eventFormGroup = this.fb.group({
        event: buildEventForm(this.fb),
        details: buildDetailForm(this.fb),
        locations: buildLocationForm(this.fb),
        goal: buildGoalForm(this.fb, eventObject),
        note: buildNoteForm(this.fb, eventObject),
        reflection: buildReflectionForm(this.fb, eventObject),
        performers: this.fb.array(
          performerData?.length
            ? performerData.map(p => buildPerformerForm(this.fb, p))
            : [buildPerformerForm(this.fb)]
        )

      })
    }
    this.originalEventValue = structuredClone(this.eventFormGroup.value); // add this

  }

  addPerformer(): void {
    const performers = this.eventFormGroup.get('performers') as FormArray;
    performers.push(buildPerformerForm(this.fb));
  }
  removePerformer(index: number): void {
    const performers = this.eventFormGroup.get('performers') as FormArray;
    if (performers.length > 0) {
      performers.removeAt(index);
    }
  }


  toggleEditDisplay(eventObject: Event): void {
    this.displayMode = this.displayMode === 'edit' ? 'display' : 'edit';
    this.toggleEditorMode(eventObject);
  }

  exitEditMode(): void {
    if (!this.eventFormGroup || !this.originalEventValue) {
      this.editor = false;
      this.displayMode = 'display';
      this.cdr.markForCheck();
      return;
    }

    const hasChanges = JSON.stringify(this.eventFormGroup.value) !== JSON.stringify(this.originalEventValue);

    if (hasChanges) {
      this.updateDialog();
    } else {
      this.editor = false;
      this.displayMode = 'display';
      this.cdr.markForCheck();
    }
  }



  selectedEvent(event: MouseEvent, notebookEvent: Event, notebookId: any) {
    const id = notebookId;
    if (!this.editor) {
      this.eventSelected = notebookEvent;
      console.log(this.eventSelected);
    }
    if (this.editor) {
      event.stopPropagation();
      this.updateDialog();
    }
  }

  truncateWebsite(url: string): string {
    try {
      return new URL(url).hostname;
    } catch (e) {
      return url;
    }
  }

  toggle(): void {
    this.isOpen = !this.isOpen;
  }

  scrollToItem(id: string) {
    const el = document.getElementById(id);
    el?.scrollIntoView({behavior: 'smooth', block: 'start', inline: 'start'});
  }

  toggleNotebook(id: number | undefined): void {
    this.selectedNotebookId = this.selectedNotebookId === id ? undefined : id;
  }

  trackByNotebookId(index: number, notebook: Notebook): number {
    return notebook.id!;
  }

  trackByEventId(index: number, event: Event): number {
    return event.id!;
  }

  selectedIndex: number | null = null;
}
