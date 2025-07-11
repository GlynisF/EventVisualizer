import {Component, ElementRef, inject, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {Performer} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

export function buildPerformerForm(fb: FormBuilder): FormGroup {
  const performer: Performer = {
    fullName: '',
    moniker: '',
    email: '',
    performanceFee: null
  };
  return buildFormGroup(fb, performer);
}

@Component({
  selector: 'app-performer',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,

  ],
  templateUrl: './performer.component.html',
  styleUrl: './performer.component.scss'
})
export class PerformerComponent implements OnChanges, OnInit {
  @Input() formGroup!: FormGroup;
  @ViewChild('fee-suffix') performanceFeeInput!: ElementRef;
  fb = inject(FormBuilder);

  showSuffix = true;



  ngOnInit() {
    this.formGroup.get('performanceFee')?.valueChanges.subscribe(val => {
      val = val?.toString().trim() ?? '';

      const isZero = val === '0';
      const isPatternMatch = /^%d(\.%d+)?$/.test(val);
      this.showSuffix = val === '' || isZero || isPatternMatch;
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const performer: Performer = {
        fullName: '',
        moniker: '',
        email: '',
        performanceFee: null
      };

      const group = buildFormGroup(this.fb, performer);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }

}
