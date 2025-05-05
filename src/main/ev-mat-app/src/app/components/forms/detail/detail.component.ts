import {Component, EventEmitter, inject, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTimepickerModule} from '@angular/material/timepicker';
import {buildDetailForm} from '../../../util/form-util';
import {debounceTime} from 'rxjs/operators';

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
export class DetailComponent implements OnInit, OnChanges {
  @Input() formGroup!: FormGroup;
  @Input() detailField?: string;

  @Output() detailChanged = new EventEmitter<any>();

  fb = inject(FormBuilder);

  ngOnInit() {
    this.formGroup = buildDetailForm(this.fb);

    this.formGroup.valueChanges
      .pipe(debounceTime(300))
      .subscribe(values => {
        this.detailChanged.emit(values);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      this.formGroup = buildDetailForm(this.fb);
    }
  }




}
