import {ChangeDetectionStrategy, Component, inject, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-goal',
  imports: [CommonModule, MatExpansionModule, MatInputModule, MatFormFieldModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './goal.component.html',
  styleUrl: './goal.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoalComponent implements OnInit {
protected goalForm!: FormGroup;
private fb = inject(FormBuilder);

  constructor(){}

  ngOnInit() {
    this.goalForm = this.fb.group({
      goal: ['']
    });
  }

  goal = new FormControl();


  onSubmit() {
    if (this.goalForm.valid) {
      console.log(this.goal.value);
    }
  }

}
