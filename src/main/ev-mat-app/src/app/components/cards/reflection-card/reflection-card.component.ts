import {Component, Input} from '@angular/core';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';

@Component({
  selector: 'app-reflection-card',
    imports: [MaterialCompsModule],
  templateUrl: './reflection-card.component.html',
  styleUrl: './reflection-card.component.scss'
})
export class ReflectionCardComponent {
  @Input() item: any;

}
