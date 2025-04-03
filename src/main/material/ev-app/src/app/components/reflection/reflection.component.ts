import {Component, inject, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-reflection',
  imports: [CommonModule, MatExpansionModule, MatInputModule, MatFormFieldModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './reflection.component.html',
  styleUrl: './reflection.component.scss'
})
export class ReflectionComponent implements OnInit{
  protected noteGroup!: FormGroup;
  private fb = inject(FormBuilder);
  reflection = new FormControl();

  constructor(){}

  ngOnInit() {
    this.noteGroup = this.fb.group({
      reflection: ['']
    });
  }

  onSubmit() {
    if (this.noteGroup.valid) {
      console.log(this.reflection.value);
    }
  }
}
