import {Component, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';

@Component({
  selector: 'app-detail-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule],
  templateUrl: './detail-card.component.html',
  styleUrl: './detail-card.component.scss'
})
export class DetailCardComponent {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() convertTimeStringToDate!: (time: string) => Date;
  @Input() detailData: any;
}
