import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatFormField, MatHint, MatInput, MatLabel} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-text-field',
  imports: [
    MatFormField, CommonModule, MatHint, MatLabel, MatFormField, ReactiveFormsModule, MatInput,
  ],
  template: `
    <div class="col">
    <mat-form-field appearance="outline" class="full-width">
      <mat-label>{{ label }}</mat-label>
      <input matInput [type]="type" [id]="id" [formControlName]="formControlName" [placeholder]="placeholder" *ngIf="required === 'required'">
      <mat-hint *ngIf="hint">{{ hint }}</mat-hint>
    </mat-form-field>
    </div>
  `,
  styleUrl: './text-field.component.scss'
})
export class TextFieldComponent {
  @Input() id = '';
  @Input() label = '';
  @Input() placeholder = '';
  @Input() hint = '';
  @Input() required: any;
  @Input() type: 'text' | 'password' | 'email' | 'number' = 'text';
  @Input() formControlName = '';
}
