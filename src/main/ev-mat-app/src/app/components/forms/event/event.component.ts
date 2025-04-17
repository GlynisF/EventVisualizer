import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Event} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';
import {MatInput, MatLabel} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-event',
  imports: [
    MatFormFieldModule,
    MatInput,
    MatLabel,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './event.component.html',
  styleUrl: './event.component.scss'
})
export class EventComponent implements OnChanges{
  @Input() formGroup!: FormGroup;
  fb = inject(FormBuilder);

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const event: Event = { eventName: '' };
      const validators = { eventName: [Validators.required] };
      const group = buildFormGroup(this.fb, event, validators);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }
}
