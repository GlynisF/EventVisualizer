import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClientService} from '../../../services/http-client.service';
import {Goal} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';

@Component({
  selector: 'app-goal',
  imports: [CommonModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule],
  templateUrl: './goal.component.html',
  styleUrl: './goal.component.scss'
})
export class GoalComponent implements OnChanges{
  @Input() formGroup!: FormGroup;
  fb = inject(FormBuilder);
  http = inject(HttpClientService);

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const goal: Goal = { goalDescription: '' };
      const group = buildFormGroup(this.fb, goal);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }

}
