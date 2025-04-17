import {Component, Input, signal} from '@angular/core';
import {MatExpansionModule, MatExpansionPanel} from '@angular/material/expansion';
import {expandCollapse, rotateToggle, scaleFadeInOut, staggeredSlideIn} from '../../util/animations';

@Component({
  animations: [expandCollapse, rotateToggle, staggeredSlideIn, scaleFadeInOut],
  selector: 'app-panel-wrapper',
  imports: [MatExpansionModule, MatExpansionPanel],
  templateUrl: './panel-wrapper.component.html',
  styleUrl: './panel-wrapper.component.scss'
})
export class PanelWrapperComponent {
  @Input() panelTitle: string = '';

  panelOpenState = signal(false);

}
