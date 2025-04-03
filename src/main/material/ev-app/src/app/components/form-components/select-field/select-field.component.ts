import {Component, Input} from '@angular/core';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-select-field',
  imports: [CommonModule, MatFormFieldModule, MatSelectModule, ReactiveFormsModule],
  template: `
    <mat-form-field>
      <mat-label>{{ label }}</mat-label>
      <mat-select [formControl]="control">
        <mat-option *ngFor="let option of options" [value]="option.value">
          {{ option.label }}
        </mat-option>
      </mat-select>
    </mat-form-field>
  `,
  styleUrl: './select-field.component.scss'
})
export class SelectFieldComponent {
  @Input() label = '';
  @Input() control!: FormControl;
  @Input() options: {value: any; label: string} [] = [];
}
