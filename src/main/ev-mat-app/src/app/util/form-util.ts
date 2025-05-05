import {FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {Detail, Event, Goal, Location, Note, Notebook, Performer, Reflection} from '../models/entity.model';

export function buildFormGroup<T extends object>(
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

export function buildNotebookForm(fb: FormBuilder): FormGroup {
  const notebook: Notebook = { id: undefined, title: '' };
  const validators = { title: [Validators.required] };
  return buildFormGroup(fb, notebook, validators);
}

export function buildEventForm(fb: FormBuilder): FormGroup {
  const event: Event = { id: undefined, eventName: '' };
  const validators = { eventName: [Validators.required] };
  return buildFormGroup(fb, event, validators);
}

export function buildLocationForm(fb: FormBuilder): FormGroup {
  return fb.group({
    id: [null],
    locationName: [''],
    address: [''],
    address2: [''],
    city: [''],
    state: [''],
    zip: [''],
    phoneNumber: [''],
    website: [''],
    accessible: [false],
    fullAddress: ['']
  });

  //return buildFormGroup(fb, location);
}

export function buildPerformerForm(fb: FormBuilder): FormGroup {
  const performer: Performer = {
    id: undefined,
    fullName: '',
    moniker: '',
    email: '',
    performanceFee: null
  };
  return buildFormGroup(fb, performer);
}

export function buildGoalForm(fb: FormBuilder): FormGroup {
  const goal: Goal = { id: undefined, goalDescription: '' };
  return buildFormGroup(fb, goal);
}

export function buildNoteForm(fb: FormBuilder): FormGroup {
  const note: Note = { id: undefined, noteDescription: '' };
  return buildFormGroup(fb, note);
}

export function buildReflectionForm(fb: FormBuilder): FormGroup {
  const reflection: Reflection = { id: undefined, reflectionDescription: '' };
  return buildFormGroup(fb, reflection);
}

export function buildDetailForm(fb: FormBuilder): FormGroup {
  const detail: Detail = {
    id: undefined,
    dateOfEvent: null,
    startTime: null,
    endTime: null,
    description: ''
  };
  return buildFormGroup(fb, detail);
}

export function buildDetailArray(fb: FormBuilder, details: Detail[]): FormArray {
  return fb.array(details.map(d => buildFormGroup(fb, d)));
}

export function buildPerformerArray(fb: FormBuilder, performers: Performer[]): FormArray {
  return fb.array(performers.map(p => buildFormGroup(fb, p)));
}

export function buildLocationArray(fb: FormBuilder, locations: Location[]): FormArray {
  return fb.array(locations.map(l => buildFormGroup(fb, l)));
}

export function buildComposedForm(fb: FormBuilder): FormGroup {
  return fb.group({
    notebook: buildNotebookForm(fb),
    event: buildEventForm(fb),
    details: fb.array([]),
    performers: fb.array([]),
    locations: fb.array([]),
    goal: buildGoalForm(fb),
    note: buildNoteForm(fb),
    reflection: buildReflectionForm(fb)
  });
}

export function patchComposedForm(fb: FormBuilder, form: FormGroup, entity: Event): void {
  if (!form || !entity) return;

  if (entity.eventName || entity.id) {
    form.get('event')?.patchValue({ id: entity.id, eventName: entity.eventName });
  }

  if (entity.details?.length) {
    form.setControl('details', buildDetailArray(fb, entity.details));
  }

  if (entity.goal) {
    form.get('goal')?.patchValue({ id: entity.goal.id, goalDescription: entity.goal.goalDescription });
  }

  if (entity.note) {
    form.get('note')?.patchValue({ id: entity.note.id, noteDescription: entity.note.noteDescription });
  }

  if (entity.reflection) {
    form.get('reflection')?.patchValue({ id: entity.reflection.id, reflectionDescription: entity.reflection.reflectionDescription });
  }

  const locations = entity.details?.flatMap(d => d.locations || []) || [];
  if (locations.length) {
    form.setControl('locations', buildLocationArray(fb, locations));
  }

  const performers = entity.details?.flatMap(d => d.performers || []) || [];
  if (performers.length) {
    form.setControl('performers', buildPerformerArray(fb, performers));
  }
}
