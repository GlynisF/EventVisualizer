import {Component, Input} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {CommonModule, NgIf} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-button',
  imports: [MatButtonModule, CommonModule, MatIconModule, NgIf],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
    @Input() type?: string = '';
    @Input() matButtonType?: string = '';
    @Input() className?: string = '';
    @Input() withIcon?: boolean = false;
    @Input() buttonText?: string = '';
    @Input() iconValue?: string = '';
    @Input() iconClass?: string = '';
}
