import {inject, Injectable} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DetailService {
  constructor(private fb: FormBuilder) {
    this.fb = inject(FormBuilder);
  }

  createDetailForm(): FormGroup {
    let eventName = new FormControl('', Validators.required);
    let eventDate = new FormControl(null);
    let startTime = new FormControl(null);
    let endTime = new FormControl(null);
    return this.fb.group({
      eventName: eventName,
      eventDate: eventDate,
      startTime: startTime,
      endTime: endTime
    });
  }

  resetForm(form: FormGroup): void {
    form.reset();
  }
}
