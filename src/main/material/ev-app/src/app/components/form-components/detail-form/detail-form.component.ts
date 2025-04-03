import {ChangeDetectionStrategy, Component, inject, OnInit, signal} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatDatepickerInputEvent, MatDatepickerModule} from '@angular/material/datepicker';
import {MatTimepickerModule} from '@angular/material/timepicker';
import {MatButtonModule} from '@angular/material/button';
import {provideMomentDateAdapter} from '@angular/material-moment-adapter';
// tslint:disable-next-line:no-duplicate-imports
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIcon} from '@angular/material/icon';

const moment = _rollupMoment || _moment;



export const MY_FORMATS = {
  parse: {
    dateInput: 'M/DD/YYYY',
    timeInput: 'hh:mm a'
  },
  display: {
    dateInput: 'ddd MMM D, YYYY',
    timeOptionLabel: 'hh:mm a ',
    timeInput: 'hh:mm a',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};



@Component({
  selector: 'app-detail-form',
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInput, MatDatepickerModule, MatTimepickerModule,
    MatButtonModule, MatExpansionModule, MatIcon],
  templateUrl: './detail-form.component.html',
  styleUrl: './detail-form.component.scss',
  providers: [provideMomentDateAdapter(undefined, {strict: true})],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DetailFormComponent implements OnInit {
  protected detailForm!: FormGroup;
  private fb = inject(FormBuilder);

  constructor() {}

  ngOnInit(): void {
    this.detailForm = this.fb.group({
      eventName: [''],
      date: [''],
      startTime: [''],
      endTime: ['']
    });
  }

  inputChanged(event: { value: any; }) {
    console.log(event.value);
  }

  changedValue(event: any) {
    console.log(event.value)
  }


  eventName = new FormControl();
  startTime = new FormControl();
  endTime = new FormControl();
  readonly date = new FormControl(moment([]));

  isDirty = false;





  events = signal<string[]>([]);

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.events.update(events => [...events, `${type}: ${event.value}`]);

  }



  onSubmit(): void {
    if (this.detailForm.valid) {
      console.log(this.detailForm.value);
        console.log(this.eventName.value);
        console.log(this.startTime.value);
        console.log(this.date.value, 'date');
        console.log(this.detailForm.get('startTime'), 'date');
    }
  }


}
