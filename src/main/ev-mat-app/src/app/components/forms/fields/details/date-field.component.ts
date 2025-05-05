import {Component, Input} from '@angular/core';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../../../materialcomps/materialcomps.module';

@Component({
  selector: 'app-date-field',
  imports: [
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MaterialCompsModule,
    ReactiveFormsModule
  ],
  template: `
    <ng-container [formGroup]="formGroup">
    <mat-form-field class="full-width">
      <mat-label>Date</mat-label>
      <input matInput id="dateOfEvent" [matDatepicker]="datepicker" [formControl]="dateOfEvent" [value]="fieldValue" placeholder="Select date of event" />
      <mat-hint align="end"><em>mm/dd/yyyy</em></mat-hint>
      <mat-datepicker-toggle matIconSuffix [for]="datepicker"></mat-datepicker-toggle>
      <mat-datepicker #datepicker></mat-datepicker>
    </mat-form-field>

    </ng-container>
  `,
  styleUrl: './date-field.component.scss'
})
export class DateFieldComponent {
  @Input() formGroup!: FormGroup;
  @Input() dateOfEvent!: FormControl;
  @Input() fieldValue?: any;


}
