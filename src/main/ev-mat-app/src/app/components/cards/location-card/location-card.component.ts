import {Component, inject, Input, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {MaterialCompsModule} from '../../../materialcomps/materialcomps.module';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {HttpClientService} from '../../../services/http-client.service';

@Component({
  selector: 'app-location-card',
  imports: [
    NgForOf,
    NgIf,
    MaterialCompsModule,
    MatSlideToggleModule,
    ReactiveFormsModule
  ],
  templateUrl: './location-card.component.html',
  styleUrl: './location-card.component.scss'
})
export class LocationCardComponent implements OnInit {
  @Input() item: any;
  @Input() editor!: boolean;
  @Input() eventSelected?: any = {details: []};
  @Input() displayMode?: string;
  @Input() formGroup!: FormGroup;
  @Input() updateEvent?: () => void;
  suggestions: { description: string; placeId: string }[] = [];
  http = inject(HttpClientService);
  fb = inject(FormBuilder);

  public locationList: any[] = [];
  public addressList: any[]= [];

  onAddressInput(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.formGroup.get('address')?.patchValue(value);
  }


  ngOnInit() {
    this.locationList = this.locations();
    const location = this.locationList[0];
    if (location && this.formGroup) {

       const fullAddress: string = `${location.address} ${location.address2 || ''}, ${location.city}, ${location.state}`;

      this.formGroup.patchValue({
        id: location.id,
        locationName: location.locationName,
        address: fullAddress,
        address2: location.address2,
        city: location.city,
        state: location.state,
        zip: location.zip,
        phoneNumber: location.phoneNumber,
        website: location.website,
        accessible: location.accessible,
      })
    }
  }

  fetchAutocompleteSuggestions(event: Event): void {
    const query = event.target as HTMLInputElement;

    if (!query || query.value.length < 3) return;

    this.http.getDataWithParams('autocomplete', {userInput: query.value})
      .subscribe({
        next: (res: any[]) => {
          this.suggestions = res.map(prediction => ({
            description: prediction.description,
            placeId: prediction.placeId
          }));
        },
        error: err => {
          console.error('Autocomplete error:', err);
        }
      });
  }

selectSuggestion(suggestion: { description: string; placeId: string }): void {
    const list = document.getElementById('autocomplete-list');
    if(list) {
      list.innerHTML = '';
    }
    this.formGroup.patchValue({
    locationName: suggestion.description,
    address: suggestion.description
  });
  this.suggestions = [];



  this.http.getData(`autocomplete/${suggestion.placeId}`)
    .subscribe({
      next: (res: any) => {
        const fullAddress: string = `${res.address} ${res.address2 || ''}, ${res.city}, ${res.state}`;

        console.log(res);
        this.formGroup.patchValue({
          locationName: res.name ?? '',
          address: fullAddress,
          address2: res.address2 ?? '',
          city: res.city ?? '',
          state: res.state ?? '',
          zip: res.zip ?? '',
          phoneNumber: res.phone_number ?? '',
          website: res.website ?? '',
          accessible: res.accessibility ?? false,
        });

      },
      error: err => console.error('Failed to fetch place details:', err)
    });
}

  trackById(index: number, item: any): number {
    return item.id;
  }

  locations(): any[] {
    const result: any[] = [];
    this.eventSelected?.details?.forEach((detail: { locations: any[] }) => {
      if (detail.locations?.length) {
        result.push(...detail.locations);
      }
    });
    return result;
  }



}
