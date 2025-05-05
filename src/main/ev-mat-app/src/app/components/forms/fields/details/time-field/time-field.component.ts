import {Component, Input, OnInit} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../../../../materialcomps/materialcomps.module';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-time-field',
  imports: [
    MaterialCompsModule,
    ReactiveFormsModule,
    NgIf
  ],
  template: `
    <ng-container [formGroup]="formGroup">
      <ng-container *ngIf="timeType === 'start'; else end">
    <mat-form-field class="start-picker">
      <mat-label>Start time</mat-label>
      <input matInput [formControlName]="timeControlValue" [matTimepicker]="timepicker"/>
      <mat-timepicker-toggle matIconSuffix [for]="timepicker"></mat-timepicker-toggle>
      <mat-timepicker #timepicker></mat-timepicker>
    </mat-form-field>
      </ng-container>
      <ng-template #end>
        <mat-form-field class="end-picker">
          <mat-label>End time</mat-label>
          <input matInput [formControlName]="timeControlValue" [matTimepicker]="timepicker" />
          <mat-timepicker-toggle matIconSuffix [for]="timepicker"></mat-timepicker-toggle>
          <mat-timepicker #timepicker></mat-timepicker>
        </mat-form-field>
      </ng-template>
    </ng-container>

  `,
  styleUrl: './time-field.component.scss'
})
export class TimeFieldComponent implements OnInit{

  @Input() formGroup!: FormGroup;
  @Input() timeControlValue!: string;
  @Input() timeValue!: any;
  @Input() timeType!: any;

  ngOnInit() {
      const control = this.formGroup.get(this.timeControlValue);

      if (control && !control.value && this.timeValue) {
      const date = this.convertTimeStringToDate(this.timeValue);
      control.setValue(date);
    }
  }

  private convertTimeStringToDate(timeString: string): Date {
      const [hours, minutes, seconds] = timeString.split(':').map(Number);
      const date = new Date();
      date.setHours(hours, minutes || 0, 0);
      return date;
    }

}
