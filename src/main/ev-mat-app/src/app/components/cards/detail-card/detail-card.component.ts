import {Component, inject, Input, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {FormService} from '../../../services/form-service.service';
import {normalizeTime} from '../../../util/form-util';
import moment from 'moment';

@Component({
  selector: 'app-detail-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule],
  templateUrl: './detail-card.component.html',
  styleUrl: './detail-card.component.scss'
})
export class DetailCardComponent implements OnInit {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() convertTimeStringToDate!: (time: string) => string;
  @Input() detailData: any;
  fb = inject(FormBuilder);
  formHelper = inject(FormService)

  ngOnInit(): void {


  }

  formatTimeDisplay(time: string): string {
    return moment(time, 'HH:mm:ss').format('h:mm A');
  }


  protected readonly normalizeTime = normalizeTime;
}
