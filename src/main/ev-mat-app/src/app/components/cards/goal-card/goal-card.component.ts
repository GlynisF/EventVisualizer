import {Component, Input, OnInit} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Event} from '../../../models/entity.model';
import {NgIf} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';


@Component({
  selector: 'app-goal-card',
  imports: [MaterialCompsModule, NgIf, ReactiveFormsModule],
  templateUrl: './goal-card.component.html',
  styleUrl: './goal-card.component.scss'
})
export class GoalCardComponent implements OnInit {
  @Input() eventSelected!: Event;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() formGroup!: FormGroup;
  

  ngOnInit() {
  }
}

