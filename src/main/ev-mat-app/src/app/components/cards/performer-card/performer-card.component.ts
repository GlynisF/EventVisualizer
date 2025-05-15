import {Component, Input} from '@angular/core';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, NgOptimizedImage, provideImgixLoader} from '@angular/common';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';


@Component({
  providers: [
    provideImgixLoader("http://localhost:4200/assets/img/"),
  ],
  selector: 'app-performer-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialCompsModule, NgOptimizedImage],
  templateUrl: './performer-card.component.html',
  styleUrl: './performer-card.component.scss'
})
export class PerformerCardComponent {
  @Input() formGroup!: FormGroup;
  @Input() displayMode: 'edit' | 'display' = 'edit';
  @Input() performerData!: any;

  constructor(private matIconRegistry: MatIconRegistry, private sanitizer: DomSanitizer) {
    this.matIconRegistry.addSvgIcon(
      'corner-dots',
      this.sanitizer.bypassSecurityTrustResourceUrl('')
    );
  }

}
