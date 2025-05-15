import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormArray, FormArrayName, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../materialcomps/materialcomps.module';

import {DetailCardComponent} from '../cards/detail-card/detail-card.component';
import {Event} from '../../models/entity.model';
import {LocationCardComponent} from '../cards/location-card/location-card.component';
import {PerformerCardComponent} from '../cards/performer-card/performer-card.component';
import {GoalCardComponent} from '../cards/goal-card/goal-card.component';
import {NoteCardComponent} from '../cards/note-card/note-card.component';
import {ReflectionCardComponent} from '../cards/reflection-card/reflection-card.component';
import moment from 'moment/moment';

@Component({
  selector: 'app-edit-event',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialCompsModule,
    DetailCardComponent,
    LocationCardComponent,
    PerformerCardComponent,
    GoalCardComponent,
    NoteCardComponent,
    ReflectionCardComponent
  ],
  templateUrl: './edit-event.component.html',
  styleUrl: './edit-event.component.scss'
})
export class EditEventComponent implements OnChanges {

  @Input() formGroup!: FormGroup;
  @Input() formArrayName!: FormArrayName;
  @Input() eventSelected!: Event;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() onAddPerformer!: () => void;
  @Input() removePerformer!: (index: number) => void;
  @Input() displayModeEvent!: (event: MouseEvent) => any;

  ngOnChanges(changes:SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const detail = this.eventSelected.details;

      if (this.eventSelected && this.formGroup) {
        this.patchEvent();

        if (detail && this.detailFormGroup) {
          detail.forEach((item: any) => {
            this.patchDetail(item);
          })
        }
      }
    }
  }

  get eventFormGroup(): FormGroup {
    return this.formGroup.get('event') as FormGroup;
  }

  get detailFormGroup(): FormGroup {
    return this.formGroup.get('details') as FormGroup;
  }

  get locationFormGroup(): FormGroup {
    return this.formGroup.get('locations') as FormGroup;
  }

  get goalFormGroup(): FormGroup {
    return this.formGroup.get('goal') as FormGroup;
  }

  get noteFormGroup(): FormGroup {
    return this.formGroup.get('note') as FormGroup;
  }

  get reflectionFormGroup(): FormGroup {
    return this.formGroup.get('reflection') as FormGroup;
  }

  get performerFormGroups(): FormGroup[] {
    const array = this.formGroup?.get('performers');
    return array instanceof FormArray ? array.controls as FormGroup[] : [];
  }

  performersFormArray(): FormArray {
    return this.formGroup?.get('performers') as FormArray;
  }

  formatFormTimeDisplay(time: string): Date {
    return moment(time, 'HH:mm:ss').toDate();
  }




  patchEvent() {
    return this.eventFormGroup.patchValue({
      id: this.eventSelected.id,
      eventName: this.eventSelected.eventName
    })
  }

  patchDetail(detail: any) {
    return this.detailFormGroup.patchValue({
      id: detail.id,
      description: detail.description,
      dateOfEvent: detail.dateOfEvent,
      startTime: this.formatFormTimeDisplay(detail.startTime),
      endTime: this.formatFormTimeDisplay(detail.endTime)
    });
  }

}
