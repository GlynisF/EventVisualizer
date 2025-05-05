import {Component, ElementRef, inject, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {fromEvent} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatListModule} from '@angular/material/list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientService} from '../../../services/http-client.service';
import {MatIcon} from '@angular/material/icon';


@Component({
  selector: 'app-location',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatListModule,
    MatIcon
  ],
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent implements OnChanges {
  @Input() formGroup!: FormGroup;
  @ViewChild('locationInput', { static: true }) locationInput!: ElementRef<HTMLInputElement>;

  fb = inject(FormBuilder);
  http = inject(HttpClientService);

  suggestions: { description: string; placeId: string }[] = [];
  hasUnitNumber = false;

  states = [
    {name: 'Alabama', abbreviation: 'AL'},
    {name: 'Alaska', abbreviation: 'AK'},
    {name: 'American Samoa', abbreviation: 'AS'},
    {name: 'Arizona', abbreviation: 'AZ'},
    {name: 'Arkansas', abbreviation: 'AR'},
    {name: 'California', abbreviation: 'CA'},
    {name: 'Colorado', abbreviation: 'CO'},
    {name: 'Connecticut', abbreviation: 'CT'},
    {name: 'Delaware', abbreviation: 'DE'},
    {name: 'District Of Columbia', abbreviation: 'DC'},
    {name: 'Federated States Of Micronesia', abbreviation: 'FM'},
    {name: 'Florida', abbreviation: 'FL'},
    {name: 'Georgia', abbreviation: 'GA'},
    {name: 'Guam', abbreviation: 'GU'},
    {name: 'Hawaii', abbreviation: 'HI'},
    {name: 'Idaho', abbreviation: 'ID'},
    {name: 'Illinois', abbreviation: 'IL'},
    {name: 'Indiana', abbreviation: 'IN'},
    {name: 'Iowa', abbreviation: 'IA'},
    {name: 'Kansas', abbreviation: 'KS'},
    {name: 'Kentucky', abbreviation: 'KY'},
    {name: 'Louisiana', abbreviation: 'LA'},
    {name: 'Maine', abbreviation: 'ME'},
    {name: 'Marshall Islands', abbreviation: 'MH'},
    {name: 'Maryland', abbreviation: 'MD'},
    {name: 'Massachusetts', abbreviation: 'MA'},
    {name: 'Michigan', abbreviation: 'MI'},
    {name: 'Minnesota', abbreviation: 'MN'},
    {name: 'Mississippi', abbreviation: 'MS'},
    {name: 'Missouri', abbreviation: 'MO'},
    {name: 'Montana', abbreviation: 'MT'},
    {name: 'Nebraska', abbreviation: 'NE'},
    {name: 'Nevada', abbreviation: 'NV'},
    {name: 'New Hampshire', abbreviation: 'NH'},
    {name: 'New Jersey', abbreviation: 'NJ'},
    {name: 'New Mexico', abbreviation: 'NM'},
    {name: 'New York', abbreviation: 'NY'},
    {name: 'North Carolina', abbreviation: 'NC'},
    {name: 'North Dakota', abbreviation: 'ND'},
    {name: 'Northern Mariana Islands', abbreviation: 'MP'},
    {name: 'Ohio', abbreviation: 'OH'},
    {name: 'Oklahoma', abbreviation: 'OK'},
    {name: 'Oregon', abbreviation: 'OR'},
    {name: 'Palau', abbreviation: 'PW'},
    {name: 'Pennsylvania', abbreviation: 'PA'},
    {name: 'Puerto Rico', abbreviation: 'PR'},
    {name: 'Rhode Island', abbreviation: 'RI'},
    {name: 'South Carolina', abbreviation: 'SC'},
    {name: 'South Dakota', abbreviation: 'SD'},
    {name: 'Tennessee', abbreviation: 'TN'},
    {name: 'Texas', abbreviation: 'TX'},
    {name: 'Utah', abbreviation: 'UT'},
    {name: 'Vermont', abbreviation: 'VT'},
    {name: 'Virgin Islands', abbreviation: 'VI'},
    {name: 'Virginia', abbreviation: 'VA'},
    {name: 'Washington', abbreviation: 'WA'},
    {name: 'West Virginia', abbreviation: 'WV'},
    {name: 'Wisconsin', abbreviation: 'WI'},
    {name: 'Wyoming', abbreviation: 'WY'}
  ];

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formGroup'] && this.formGroup) {
      const locationControls = this.fb.group({
        locationName: [''],
        address: [''],
        address2: [''],
        city: [''],
        state: [''],
        zip: [''],
        phoneNumber: [''],
        website: [''],
        accessible: [false],
      });

      Object.keys(locationControls.controls).forEach(key => {
        if (!this.formGroup.get(key)) {
          this.formGroup.addControl(key, locationControls.get(key)!);
        }
      });
    }
  }

  fetchAutocompleteSuggestions(query: string): void {
    if (!query || query.length < 3) return;

    this.http.getDataWithParams('autocomplete', { userInput: query })
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


  ngAfterViewInit(): void {
    fromEvent(this.locationInput.nativeElement, 'input')
      .pipe(
        map((event: Event) => (event.target as HTMLInputElement).value),
        debounceTime(300),
        distinctUntilChanged()
      )
      .subscribe(value => {
        this.fetchAutocompleteSuggestions(value);
      });
  }

  selectSuggestion(suggestion: { description: string; placeId: string }): void {
    this.formGroup.patchValue({
      locationName: suggestion.description,
      address: suggestion.description
    });
    this.suggestions = [];

    // Optional: lookup full place details from your backend
    this.http.getData(`autocomplete/${suggestion.placeId}`)
  .subscribe({
      next: (res: any) => {
        console.log(res);
        this.formGroup.patchValue({
          locationName: res.name != null ? res.name : suggestion.description,
          address: res.address ?? '',
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

  clearLocationForm() {
    this.formGroup.get("locationFormGroup")?.reset();
  }

}
