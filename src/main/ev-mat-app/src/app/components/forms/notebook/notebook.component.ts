import {Component, inject, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientService} from '../../../services/http-client.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatDividerModule} from '@angular/material/divider';

@Component({
  selector: 'app-notebook',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDividerModule
  ],
  templateUrl: './notebook.component.html',
  styleUrl: './notebook.component.scss'
})
export class NotebookComponent implements OnInit, OnChanges {
  @Input() formGroup!: FormGroup;

  fb = inject(FormBuilder);
  http = inject(HttpClientService);
  notebooks: any[] = [];
  newNotebook = false;
  selectedNotebookId: number | null = null;

  ngOnInit(): void {
    this.http.getData('service/getAllNotebooks/4').subscribe({
      next: (response) => this.notebooks = response,
      error: (err) => console.error('Error fetching notebooks:', err)
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      if (!this.formGroup.contains('id')) {
        this.formGroup.addControl('id', new FormControl(null));
      }
      if (!this.formGroup.contains('title')) {
        this.formGroup.addControl('title', new FormControl(null));
      }
    }
  }

  checkSelectedValue(value: string | number): void {
    this.newNotebook = value === 'create';

    const idControl = this.formGroup.get('id');
    const titleControl = this.formGroup.get('title');

    if (this.newNotebook) {
      this.selectedNotebookId = null;
      idControl?.clearValidators();
      idControl?.setValue(0);
      titleControl?.setValidators([Validators.required]);
    } else {
      this.selectedNotebookId = +value;
      titleControl?.clearValidators();
      titleControl?.setValue(null);
      idControl?.setValidators([Validators.required]);
      idControl?.setValue(this.selectedNotebookId);
    }

    idControl?.updateValueAndValidity();
    titleControl?.updateValueAndValidity();
  }

  checkForUserInput(): void {
    const title = this.formGroup.get('title')?.value?.trim();
    if (!title) {
      this.newNotebook = false;
      this.formGroup.get('title')?.clearValidators();
      this.formGroup.get('title')?.setValue(null);
      this.formGroup.get('id')?.setValue(null);
      this.formGroup.get('title')?.updateValueAndValidity();
    }
  }

}
