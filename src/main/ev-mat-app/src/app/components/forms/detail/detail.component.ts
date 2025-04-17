import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTimepickerModule} from '@angular/material/timepicker';
import {Detail} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatTimepickerModule
  ],
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnChanges {
  @Input() formGroup!: FormGroup;
  fb = inject(FormBuilder);

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const detail: Detail = {
        dateOfEvent: null,
        startTime: null,
        endTime: null,
        description: ''
      };

      const group = buildFormGroup(this.fb, detail);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }
}
