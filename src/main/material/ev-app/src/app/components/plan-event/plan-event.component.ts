import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DetailFormComponent} from '../form-components/detail-form/detail-form.component';
import {GoalComponent} from '../goal/goal.component';
import {MatAccordion} from '@angular/material/expansion';
import {PerformerComponent} from '../performer/performer.component';
import {NoteComponent} from '../note/note.component';
import {LocationFormComponent} from '../location-form/location-form.component';

@Component({
  selector: 'app-plan-event',
  imports: [CommonModule, RouterModule, DetailFormComponent, GoalComponent, MatAccordion, PerformerComponent, NoteComponent, LocationFormComponent],
  templateUrl: './plan-event.component.html',
  styleUrl: './plan-event.component.scss'
})
export class PlanEventComponent implements OnInit {

  constructor() {}

  ngOnInit() {
    console.log('In Plan Event Component');
  }

}
