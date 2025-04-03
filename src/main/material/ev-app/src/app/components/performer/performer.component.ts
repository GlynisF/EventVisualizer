import {Component, inject, OnInit} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-performer',
  imports: [CommonModule, MatExpansionModule, MatInputModule, MatFormFieldModule, MatIconModule, ReactiveFormsModule],
  templateUrl: './performer.component.html',
  styleUrl: './performer.component.scss'
})
export class PerformerComponent implements OnInit {
  protected performerGroup!: FormGroup;
  private fb = inject(FormBuilder);
  fullName = new FormControl();
  moniker = new FormControl();
  email = new FormControl();
  performanceFee = new FormControl();

  constructor(){}

  ngOnInit() {
    this.performerGroup = this.fb.group({
      fullName: [''],
      moniker: [''],
      email: [''],
      performanceFee: ['']
    });
  }
    onSubmit() {
      if (this.performerGroup.valid) {
        console.log(this.performerGroup.value);
        console.log(this.performanceFee.value);
        console.log(this.email.value);
        console.log(this.fullName.value);
        console.log(this.moniker.value);
      }
    }



}
