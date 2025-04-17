import {FormBuilder, FormGroup, ValidatorFn} from '@angular/forms';

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
