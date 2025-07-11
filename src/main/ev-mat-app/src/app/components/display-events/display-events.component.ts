import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';
import {CommonModule} from '@angular/common';
import {PerformerCardComponent} from '../cards/performer-card/performer-card.component';
import {DetailCardComponent} from '../cards/detail-card/detail-card.component';
import {LocationCardComponent} from '../cards/location-card/location-card.component';
import {GoalCardComponent} from '../cards/goal-card/goal-card.component';
import {NoteCardComponent} from '../cards/note-card/note-card.component';
import {ReflectionCardComponent} from '../cards/reflection-card/reflection-card.component';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-display-events',
  imports: [CommonModule, MaterialComponents, PerformerCardComponent, DetailCardComponent, LocationCardComponent, GoalCardComponent, NoteCardComponent, ReflectionCardComponent,],
  templateUrl: './display-events.component.html',
  styleUrl: './display-events.component.scss'
})
export class DisplayEventsComponent implements OnChanges {
  @Input() eventSelected!: any;
  @Input() convertTimeStringToDate!: (time: string) => string;
  @Input() displayMode: 'edit' | 'display' = 'display';

  constructor(private iconRegistry: MatIconRegistry, private sanitizer: DomSanitizer) {
    this.iconRegistry.addSvgIcon(
      'microphone',
      this.sanitizer.bypassSecurityTrustResourceUrl('/microphone.svg')
    );
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['eventSelected']) {
      console.log('New event selected:', this.eventSelected);
    }
  }
}
