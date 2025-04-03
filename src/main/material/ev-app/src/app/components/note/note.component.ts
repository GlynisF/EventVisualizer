import {Component, inject, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-note',
  imports: [CommonModule, MatExpansionModule, MatInputModule, MatFormFieldModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './note.component.html',
  styleUrl: './note.component.scss'
})
export class NoteComponent implements OnInit {
  protected noteGroup!: FormGroup;
  private fb = inject(FormBuilder);
  note = new FormControl();

  constructor(){}

  ngOnInit() {
    this.noteGroup = this.fb.group({
      note: ['']
    });
  }

  onSubmit() {
    if (this.noteGroup.valid) {
      console.log(this.note.value);
    }
  }
}
