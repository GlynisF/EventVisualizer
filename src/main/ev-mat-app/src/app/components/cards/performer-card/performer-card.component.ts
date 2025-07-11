import {Component, Input} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {MatTooltip} from '@angular/material/tooltip';

@Component({
  providers: [],
  selector: 'app-performer-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule, MatTooltip],
  templateUrl: './performer-card.component.html',
  styleUrl: './performer-card.component.scss'
})
export class PerformerCardComponent {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() performerData!: any;
  @Input() onAddPerformer!: () => void;
  @Input() removePerformer!: (index: number) => void;
  @Input() performerFormGroups!: any;
  @Input() i!: number;


  constructor(private iconRegistry: MatIconRegistry, private sanitizer: DomSanitizer) {
    this.iconRegistry.addSvgIcon(
      'performer',
      this.sanitizer.bypassSecurityTrustResourceUrl('/performer.svg')
    );
  }

}
