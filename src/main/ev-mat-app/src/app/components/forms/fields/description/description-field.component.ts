import {ChangeDetectorRef, Component, inject, Input, OnInit} from '@angular/core';
import {MaterialCompsModule} from '../../../../materialcomps/materialcomps.module';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-description-field',
  imports: [MaterialCompsModule, ReactiveFormsModule, CommonModule],
  template: `
    <ng-container [formGroup]="formGroup">
      <mat-form-field class="full-width">
        <mat-label>Description</mat-label>
        <textarea matInput [formControlName]="description" [value]="descValue" id="description" rows="5"></textarea>
      </mat-form-field>
    </ng-container>
  `,
  styleUrl: './description-field.component.scss'

})
export class DescriptionFieldComponent implements OnInit  {
  @Input() formGroup!: FormGroup;
  @Input() description!: any;
  @Input() descValue!: any;
  @Input() holder!: string;
  @Input() item!: any;
  cdr = inject(ChangeDetectorRef);

  ngOnInit() {
    const control = this.formGroup.get(this.description);

    if (control && !control.value && this.descValue) {
      control.setValue(this.item.data.description);
    }


    if (control?.dirty) {
      control.setValue(control.value);
      this.cdr.detectChanges();
    }


  }
}
