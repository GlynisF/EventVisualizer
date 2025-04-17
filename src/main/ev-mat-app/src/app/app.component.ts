import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavToolbarComponent} from './components/nav-toolbar/nav-toolbar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavToolbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Event Visualizer Homepage';
}
