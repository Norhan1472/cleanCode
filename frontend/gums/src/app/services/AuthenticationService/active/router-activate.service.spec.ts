import { TestBed } from '@angular/core/testing';

import { RouterActivateService } from './router-activate.service';

describe('RouterActivateService', () => {
  let service: RouterActivateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouterActivateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
