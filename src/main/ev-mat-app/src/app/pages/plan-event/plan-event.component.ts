import {Component, inject, viewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {MatAccordion} from '@angular/material/expansion';

import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NotebookComponent} from '../../components/forms/notebook/notebook.component';
import {PanelWrapperComponent} from '../../components/panel-wrapper/panel-wrapper.component';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientService} from '../../services/http-client.service';
import {EventComponent} from '../../components/forms/event/event.component';
import {DetailComponent} from '../../components/forms/detail/detail.component';
import {LocationComponent} from '../../components/forms/location/location.component';
import {GoalComponent} from '../../components/forms/goal/goal.component';
import {ReflectionComponent} from '../../components/forms/reflection/reflection.component';
import {NoteComponent} from '../../components/forms/note/note.component';
import {buildPerformerForm, PerformerComponent} from '../../components/forms/performer/performer.component';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDividerModule} from '@angular/material/divider';
import {fadeInOut, rotateToggle, slideInOut} from '../../util/animations';


@Component({
  animations: [slideInOut, fadeInOut, rotateToggle],
  selector: 'app-plan-event',
  imports: [
    CommonModule, RouterModule, MatAccordion, ReactiveFormsModule, PanelWrapperComponent, NotebookComponent,
    MatButtonModule, EventComponent, DetailComponent, LocationComponent, GoalComponent, ReflectionComponent,
    PerformerComponent, NoteComponent, MatIconModule, MatTooltipModule, MatDividerModule],
  templateUrl: './plan-event.component.html',
  styleUrl: './plan-event.component.scss'
})
export class PlanEventComponent {
  planEventForm: FormGroup;
  private fb = inject(FormBuilder);
  isOpen = false;
  accordion = viewChild.required(MatAccordion);
  url?: string;

  constructor(private http: HttpClientService) {
    this.planEventForm = this.fb.group({
      notebook: this.fb.group({}),
      event: this.fb.group({}),
      detail: this.fb.group({}),
      location: this.fb.group({}),
      goal: this.fb.group({}),
      note: this.fb.group({}),
      reflection: this.fb.group({}),
      performers: this.fb.array([buildPerformerForm(this.fb)])
    })
  }

  openPanel() {
    this.isOpen = !this.isOpen;
  }

  closePanel() {
    this.isOpen = false;
  }

  trackByIndex(index: number): number {
    return index;
  }


  get notebookFormGroup(): FormGroup {
    return this.planEventForm.get('notebook') as FormGroup;
  }

  get eventFormGroup(): FormGroup {
    return this.planEventForm.get('event') as FormGroup;
  }

  get detailFormGroup(): FormGroup {
    return this.planEventForm.get('detail') as FormGroup;
  }

  get locationFormGroup(): FormGroup {
    return this.planEventForm.get('location') as FormGroup;
  }

  get goalFormGroup(): FormGroup {
    return this.planEventForm.get('goal') as FormGroup;
  }

  get noteFormGroup(): FormGroup {
    return this.planEventForm.get('note') as FormGroup;
  }

  get reflectionFormGroup(): FormGroup {
    return this.planEventForm.get('reflection') as FormGroup;
  }

  get performerFormGroups(): FormGroup[] {
    return (this.planEventForm.get('performers') as FormArray).controls as FormGroup[];
  }

  get performers(): FormArray {
    return this.planEventForm.get('performers') as FormArray;
  }

  addPerformer(): void {
    this.performers.push(buildPerformerForm(this.fb));
  }

  removePerformer(index: number): void {
    if (index > 0) {
      this.performers.removeAt(index);
    }
  }

  private formatDetailFields(detail: any): any {
    return {
      ...detail,
      dateOfEvent: new Date(detail.dateOfEvent).toISOString().split('T')[0],
      startTime: new Date(detail.startTime).toTimeString().split(' ')[0],
      endTime: new Date(detail.endTime).toTimeString().split(' ')[0],
    };
  }

  buildPostUrl(userId: any) {
    const formData = this.planEventForm.value;
    const notebookId = formData.notebook?.id;
    if (notebookId === 0) {
      return this.url = `service/new/user/${userId}`;
    } else {
      return this.url = `service/new/notebook/${notebookId}`;
    }
  }

  onSubmit(): void {
    if (this.planEventForm.valid) {
      const formValues = this.planEventForm.value;
      console.log(formValues);

      formValues.detail = this.formatDetailFields(formValues.detail);

      if (!Array.isArray(formValues.performers)) {
        formValues.performers = [formValues.performers];
      }

      formValues.performers = formValues.performers.map((p: any) => ({
        ...p,
        performanceFee: Number(p.performanceFee)
      }));

  console.log(formValues);
      this.url = this.buildPostUrl(4);

      this.http.postData(this.url, formValues).subscribe({
        next: (response) => {
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

  protected readonly FormGroup = FormGroup;
}
