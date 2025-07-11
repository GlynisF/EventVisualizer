import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavToolbarComponent} from './components/nav-toolbar/nav-toolbar.component';
import {MdbCheckboxModule} from 'mdb-angular-ui-kit/checkbox';
import {MatToolbar} from '@angular/material/toolbar';
import {MaterialComponents} from './materialcomps/materialcomps.module';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavToolbarComponent, MdbCheckboxModule, MatToolbar, MaterialComponents],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Event Visualizer Homepage';

}
