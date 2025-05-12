import {Injectable} from '@angular/core';
import {buildPerformerForm} from '../components/forms/performer/performer.component';
import {FormArray, FormBuilder, FormGroup, ValidatorFn} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormService {
  private readonly _eventForm!: FormGroup;

  constructor(private fb: FormBuilder) {
    this._eventForm = this.fb.group({
      notebook: this.fb.group({}),
      event: this.fb.group({}),
      detail: this.fb.group({}),
      location: this.fb.group({}),
      goal: this.fb.group({}),
      note: this.fb.group({}),
      reflection: this.fb.group({}),
      performers: this.fb.array([buildPerformerForm(this.fb)])
    });
  }

  getEventForm(): FormGroup {
    return this._eventForm;
  }

  buildFormGroups<T extends object>(
    fb: FormBuilder,
    model: T,
    validators?: { [K in keyof T]?: ValidatorFn[] }
  ): FormGroup {
    const group: { [key: string]: any } = {};
    for (const key of Object.keys(model)) {
      const value = model[key as keyof T] ?? null;
      const controlValidators = validators?.[key as keyof T] ?? [];
      group[key] = [value, controlValidators];
    }
    return fb.group(group);
  }

  notebookFormGroup(form: FormGroup): FormGroup {
    return form.get('notebook') as FormGroup;
  }

  eventFormGroup(form: FormGroup): FormGroup {
    return form.get('event') as FormGroup;
  }

  detailFormGroup(form: FormGroup): FormGroup {
    return form.get('detail') as FormGroup;
  }

  locationFormGroup(form: FormGroup): FormGroup {
    return form.get('location') as FormGroup;
  }

  goalFormGroup(form: FormGroup): FormGroup {
    return form.get('goal') as FormGroup;
  }

  noteFormGroup(form: FormGroup): FormGroup {
    return form.get('note') as FormGroup;
  }

  reflectionFormGroup(form: FormGroup): FormGroup {
    return form.get('reflection') as FormGroup;
  }

  performerFormGroups(form: FormGroup): FormGroup[] {
    return (form.get('performers') as FormArray).controls as FormGroup[];
  }

  performers(form: FormGroup): FormArray {
    return form.get('performers') as FormArray;
  }

  addPerformer(this: any): void {
    this.performers.push(buildPerformerForm(this.fb));
  }


  removePerformer(this: any, index: number): void {
    if (index > 0) {
      this.performers.removeAt(index);
    }
  }

  formatDetailFields(detail: any): any {
    return {
      ...detail,
      dateOfEvent: new Date(detail.dateOfEvent).toISOString().split('T')[0],
      startTime: new Date(detail.startTime).toTimeString().split(' ')[0],
      endTime: new Date(detail.endTime).toTimeString().split(' ')[0],
    };
  }
}

