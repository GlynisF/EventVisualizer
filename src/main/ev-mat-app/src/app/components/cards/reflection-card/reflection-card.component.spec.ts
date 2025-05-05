import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ReflectionCardComponent} from './reflection-card.component';

describe('ReflectionCardComponent', () => {
  let component: ReflectionCardComponent;
  let fixture: ComponentFixture<ReflectionCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReflectionCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReflectionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
