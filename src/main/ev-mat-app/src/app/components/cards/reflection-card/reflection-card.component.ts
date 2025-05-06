import {Component, Input} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Event} from '../../../models/entity.model';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-reflection-card',
  imports: [MaterialCompsModule, NgIf, ReactiveFormsModule],
  templateUrl: './reflection-card.component.html',
  styleUrl: './reflection-card.component.scss'
})
export class ReflectionCardComponent {
  @Input() eventSelected!: Event;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() formGroup!: FormGroup;

}
