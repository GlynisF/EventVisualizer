import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Note} from '../../../models/entity.model';
import {buildFormGroup} from '../../../util/form-util';

@Component({
  selector: 'app-note',
  imports: [CommonModule, MatInputModule, MatFormFieldModule, ReactiveFormsModule],
  templateUrl: './note.component.html',
  styleUrl: './note.component.scss'
})
export class NoteComponent implements OnChanges{
@Input() formGroup!: FormGroup;
fb = inject(FormBuilder);
http = inject(HttpClient)

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const note: Note = { noteDescription: '' };
      const group = buildFormGroup(this.fb, note);

      Object.keys(group.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, group.get(key)!);
        }
      });
    }
  }
}
