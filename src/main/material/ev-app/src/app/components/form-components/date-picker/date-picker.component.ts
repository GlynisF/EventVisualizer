import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [MatFormFieldModule, MatInputModule, MatDatepickerModule, MatButtonModule],
  providers: [provideNativeDateAdapter()],
  selector: 'app-date-picker',
  styleUrl: './date-picker.component.scss',
  template: `
    <div class="col">
    <mat-form-field class="full-width">
      <mat-label>Choose a date</mat-label>
      <input matInput name="eventDate" id="eventDate" [matDatepicker]="datepicker" />
      <mat-hint>MM/DD/YYYY</mat-hint>
      <mat-datepicker-toggle matIconSuffix [for]="datepicker"></mat-datepicker-toggle>
      <mat-datepicker #datepicker>
        <mat-datepicker-actions>
          <button mat-button matDatepickerCancel>Cancel</button>
          <button mat-raised-button matDatepickerApply>Apply</button>
        </mat-datepicker-actions>
      </mat-datepicker>
    </mat-form-field>
    </div>
  `
})
export class DatePickerComponent {

}
