import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MaterialComponents} from '../../materialcomps/materialcomps.module';
import {CommonModule} from '@angular/common';
import {PerformerCardComponent} from '../cards/performer-card/performer-card.component';
import {DetailCardComponent} from '../cards/detail-card/detail-card.component';
import {LocationCardComponent} from '../cards/location-card/location-card.component';

@Component({
  selector: 'app-display-events',
  imports: [CommonModule, MaterialComponents, PerformerCardComponent, DetailCardComponent, LocationCardComponent,],
  templateUrl: './display-events.component.html',
  styleUrl: './display-events.component.scss'
})
export class DisplayEventsComponent implements OnChanges {
  @Input() eventSelected!: any;
  @Input() convertTimeStringToDate!: (time: string) => Date;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['eventSelected']) {
      console.log('New event selected:', this.eventSelected);
    }
  }
}
