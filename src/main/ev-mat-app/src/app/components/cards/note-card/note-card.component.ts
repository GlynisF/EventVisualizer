import {Component, Input} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {Event} from '../../../models/entity.model';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';

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
  placeholder:string = "Keep track of tasks, deadlines & important info";

  constructor(private iconRegistry: MatIconRegistry, private sanitizer: DomSanitizer) {
    this.iconRegistry.addSvgIcon(
      'note',
      this.sanitizer.bypassSecurityTrustResourceUrl('/note.svg')
    );
  }
}
