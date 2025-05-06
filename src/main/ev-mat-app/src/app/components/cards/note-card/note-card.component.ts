import {Component, Input} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Event} from '../../../models/entity.model';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-note-card',
  imports: [MaterialCompsModule, NgIf, ReactiveFormsModule],
  templateUrl: './note-card.component.html',
  styleUrl: './note-card.component.scss'
})
export class NoteCardComponent {
  @Input() eventSelected!: Event;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() formGroup!: FormGroup;

}
