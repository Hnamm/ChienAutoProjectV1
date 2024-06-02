import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanEstimateProductComponent } from './loan-estimate-product.component';

describe('LoanEstimateProductComponent', () => {
  let component: LoanEstimateProductComponent;
  let fixture: ComponentFixture<LoanEstimateProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanEstimateProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanEstimateProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
