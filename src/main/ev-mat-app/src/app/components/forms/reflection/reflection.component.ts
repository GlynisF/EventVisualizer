import {Component, inject, Input, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Reflection} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-reflection',
  imports: [MatInputModule, MatFormFieldModule, CommonModule, ReactiveFormsModule],
  templateUrl: './reflection.component.html',
  styleUrl: './reflection.component.scss'
})
export class ReflectionComponent {
  @Input() formGroup!: FormGroup;
  fb = inject(FormBuilder);
  http = inject(HttpClient)

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const reflection: Reflection = { reflectionDescription: '' };
      const group = buildFormGroup(this.fb, reflection);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }
}
