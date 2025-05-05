import {Component, inject, Inject, Input} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule} from '@angular/material/dialog';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClientService} from '../../services/http-client.service';
import {CommonModule} from '@angular/common';
import {Notebook} from '../../models/entity.model';


@Component({
  selector: 'app-dialog',
  imports: [CommonModule, MatDialogModule, MaterialComponents, ReactiveFormsModule],
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.scss'
})
export class DialogComponent  {
  @Input() formGroup!: FormGroup;
  fb = inject(FormBuilder);
  http = inject(HttpClientService);
  notebook: Notebook | undefined;
  @Input() eventForm!: FormGroup;
  @Input() addNotebook!: boolean;



  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.formGroup = this.fb.group({
      title: ['']
    });
  }

  submitNotebook() {
    if (this.formGroup.valid) {
      const notebookValues = { notebook: this.formGroup.value };

      console.log(notebookValues);
      this.http.postData(`service/add-notebook/4`, notebookValues).subscribe({
        next: (response) => {
          console.log(response);
          console.log('Successfully submitted:', response);
        },
        error: (error) => {
          console.error('Error submitting form:', error);
        },
        complete: () => {
          console.log('Request complete');
        }
      });
    }
  }

}
