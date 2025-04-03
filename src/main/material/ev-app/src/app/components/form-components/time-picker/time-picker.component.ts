import {Component, Input} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';
import {MatTimepicker, MatTimepickerInput, MatTimepickerToggle} from '@angular/material/timepicker';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-time-picker',
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatIconModule, MatTimepickerInput, MatTimepickerToggle, MatTimepicker],
  providers: [provideNativeDateAdapter()],
  template: `
    <div class="col">
    <mat-form-field appearance="outline" class="full-width">
      <mat-label>{{ label }}</mat-label>

      <input matInput [matTimepicker]="picker" [formControl]="control">

      <mat-timepicker-toggle matIconSuffix [for]="picker"></mat-timepicker-toggle>

      <mat-timepicker #picker></mat-timepicker>
    </mat-form-field>
    </div>
  `,
  styleUrl: './time-picker.component.scss'
})
export class TimePickerComponent {
  @Input() label = '';
  @Input() control!: FormControl;
}
