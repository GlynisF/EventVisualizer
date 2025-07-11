import {Component, Input, OnInit} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Event} from '../../../models/entity.model';
import {NgIf} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';


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
  placeholder:string = "How do you envision your event?"

  constructor(private iconRegistry: MatIconRegistry, private sanitizer: DomSanitizer) {
    this.iconRegistry.addSvgIcon(
      'disco',
      this.sanitizer.bypassSecurityTrustResourceUrl('/disco_ball.svg')
    );
  }

  ngOnInit() {
  }
}

