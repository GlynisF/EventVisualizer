import {Component, Input, OnInit} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';

@Component({
  selector: 'app-performer-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule],
  templateUrl: './performer-card.component.html',
  styleUrl: './performer-card.component.scss'
})
export class PerformerCardComponent implements OnInit {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() performerData!: any;


  ngOnInit() {


  }


}
