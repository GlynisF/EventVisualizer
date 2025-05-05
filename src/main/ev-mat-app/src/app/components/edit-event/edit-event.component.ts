import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../materialcomps/materialcomps.module';

import {DetailCardComponent} from '../cards/detail-card/detail-card.component';
import {PerformerCardComponent} from '../cards/performer-card/performer-card.component';
import {Event} from '../../models/entity.model';
import {LocationCardComponent} from '../cards/location-card/location-card.component';

@Component({
  selector: 'app-edit-event',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialCompsModule,
    DetailCardComponent,
    PerformerCardComponent,
    LocationCardComponent
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

}
