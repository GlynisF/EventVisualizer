import {Component, Input} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';


@Component({
  selector: 'app-goal-card',
  imports: [MaterialCompsModule],
  templateUrl: './goal-card.component.html',
  styleUrl: './goal-card.component.scss'
})
export class GoalCardComponent {
  @Input() item: any;

}
