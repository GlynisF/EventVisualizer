import {NgModule} from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListItem, MatListModule, MatNavList} from '@angular/material/list';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatRippleModule} from '@angular/material/core';
import {MatTimepickerModule} from '@angular/material/timepicker';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatAccordion, MatExpansionModule} from '@angular/material/expansion';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import {MatTabsModule} from '@angular/material/tabs';
import {CdkAccordion} from '@angular/cdk/accordion';


export const MaterialComponents = [
  MatButtonModule,
  MatToolbarModule,
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatTabsModule,
  MatInputModule,
  MatFormFieldModule,
  MatCardModule,
  MatGridListModule,
  MatRippleModule,
  MatDatepickerModule,
  MatTimepickerModule,
  MatExpansionModule,
  MatAccordion,
  CdkAccordion,
  MatButtonModule,
  MatSelectModule,
  MatRadioModule,
  MatNavList,
  MatListItem,


]


@NgModule({
  exports: [MaterialComponents],
  imports: [MaterialComponents],
})
export class MaterialCompsModule { }
