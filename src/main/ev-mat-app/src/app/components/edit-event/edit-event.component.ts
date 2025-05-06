import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../materialcomps/materialcomps.module';

import {DetailCardComponent} from '../cards/detail-card/detail-card.component';
import {PerformerCardComponent} from '../cards/performer-card/performer-card.component';
import {Event} from '../../models/entity.model';
import {LocationCardComponent} from '../cards/location-card/location-card.component';
import {GoalCardComponent} from '../cards/goal-card/goal-card.component';
import {NoteCardComponent} from '../cards/note-card/note-card.component';
import {ReflectionCardComponent} from '../cards/reflection-card/reflection-card.component';

@Component({
  selector: 'app-edit-event',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialCompsModule,
    DetailCardComponent,
    PerformerCardComponent,
    LocationCardComponent,
    GoalCardComponent,
    NoteCardComponent,
    ReflectionCardComponent
  ],
  templateUrl: './edit-event.component.html',
  styleUrl: './edit-event.component.scss'
})
export class EditEventComponent {
  @Input() formGroup!: FormGroup;
  @Input() eventSelected!: Event;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() convertTimeStringToDate!: (time: string) => Date;

  get detailsFormArray(): FormGroup[] {
    return (this.formGroup.get('details') as any)?.controls || [];
  }

  get performersFormArray(): FormGroup[] {
    return (this.formGroup.get('performers') as any)?.controls || [];
  }

  eventGroup(): FormGroup {
    return this.formGroup.get('event') as FormGroup;
  }

  get locationForm(): FormGroup {
    return this.formGroup.get('location') as FormGroup;
  }

  get goalGroup(): FormGroup {
    return this.formGroup.get('goal') as FormGroup;
  }

  get noteGroup(): FormGroup {
    return this.formGroup.get('note') as FormGroup;
  }

  get reflectionGroup(): FormGroup {
    return this.formGroup.get('reflection') as FormGroup;
  }

}
