import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanMoneyTableComponent } from './loan-money-table.component';

describe('LoanMoneyTableComponent', () => {
  let component: LoanMoneyTableComponent;
  let fixture: ComponentFixture<LoanMoneyTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanMoneyTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanMoneyTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
