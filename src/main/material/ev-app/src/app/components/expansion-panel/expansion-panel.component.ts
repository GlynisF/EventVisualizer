import {AfterViewInit, Component, EventEmitter, Input, Output} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {NgComponentOutlet} from '@angular/common';

@Component({
  selector: 'app-expansion-panel',
  imports: [
    MatExpansionModule,
    NgComponentOutlet
  ],
  templateUrl: './expansion-panel.component.html',
  styleUrl: './expansion-panel.component.scss'
})
export class ExpansionPanelComponent implements AfterViewInit {
  @Input() title: string = '';
  @Input() panelComponent!: any;
  @Output() opened= new EventEmitter<void>();

  ngAfterViewInit() {
  }
  onPanelOpen(): void { {
    this.opened.emit();
    console.log(`${this.title} panel is now opened.`);
  }}

}
