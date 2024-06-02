import { TestBed } from '@angular/core/testing';

import { LoanMoneyService } from './loan-money.service';

describe('LoanMoneyService', () => {
  let service: LoanMoneyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanMoneyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
