import {Component, Input} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Performer} from '../../../models/entity.model';

@Component({
  selector: 'app-performer-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule],
  templateUrl: './performer-card.component.html',
  styleUrl: './performer-card.component.scss'
})
export class PerformerCardComponent {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() performerData!: Performer;
}
