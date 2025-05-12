import {FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import type {Detail, Event, Goal, Location, Note, Notebook, Performer, Reflection} from '../models/entity.model';

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
  const event: Event = { id: undefined, eventName: ''};
  const validators = { eventName: [Validators.required] };
  return buildFormGroup(fb, event, validators);
}

export function buildLocationForm(fb: FormBuilder): FormGroup {
  const locations: Location = {
    id: undefined,
    locationName: '',
    address: '',
    address2: '',
    city: '',
    state: '',
    zip: '',
    phoneNumber: '',
    website: '',
    accessible: false,
  };

  return buildFormGroup(fb, locations);
}

export function buildPerformerForm(fb: FormBuilder, data?: Performer): FormGroup {
  const performer: Performer = {
    id: data?.id,
    fullName: data?.fullName || '',
    moniker: data?.moniker || '',
    email: data?.email || '',
    performanceFee: data?.performanceFee ?? null
  };
  return buildFormGroup(fb, performer);
}

export function buildGoalForm(fb: FormBuilder, event: Event): FormGroup {
  const goal: Goal = { eventId: event.id || null, goalDescription: '' };
  return buildFormGroup(fb, goal);
}

export function buildNoteForm(fb: FormBuilder, event: Event): FormGroup {
  const note: Note = { eventId: event.id || null, noteDescription: '' };
  return buildFormGroup(fb, note);
}

export function buildReflectionForm(fb: FormBuilder, event: Event): FormGroup {
  const reflection: Reflection = { eventId: event.id || null, reflectionDescription: '' };
  return buildFormGroup(fb, reflection);
}

export function buildDetailForm(fb: FormBuilder): FormGroup {
  const details: Detail = {
    id: undefined,
    dateOfEvent: null,
    startTime: '',
    endTime: '',
    description: '',
  };
  return buildFormGroup(fb, details);
}

export function buildDetailArray(fb: FormBuilder, details: Detail[]): FormArray {
  return fb.array(details.map(d => buildFormGroup(fb, d)));
}

export function buildPerformerArray(fb: FormBuilder, performers: Performer[]): FormGroup[] {
  return performers.map(p => buildPerformerForm(fb, p));
}

export function buildLocationArray(fb: FormBuilder, locations: Location[]): FormArray {
  return fb.array(locations.map(l => buildFormGroup(fb, l)));
}
export function normalizeTime(value: string | Date | null): string {
  if (!value) return '';
  if (typeof value === 'string') return value;
  return value.toISOString().substring(11, 19);
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
    form.get('goal')?.patchValue({ eventId: entity.id, goalDescription: entity.goal.goalDescription });
  }

  if (entity.note) {
    form.get('note')?.patchValue({ eventId: entity.id, noteDescription: entity.note.noteDescription });
  }

  if (entity.reflection) {
    form.get('reflection')?.patchValue({ eventId: entity.id, reflectionDescription: entity.reflection.reflectionDescription });
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
