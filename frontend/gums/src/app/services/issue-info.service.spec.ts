import { TestBed } from '@angular/core/testing';

import { IssueInfoService } from './issue-info.service';

describe('IssueInfoService', () => {
  let service: IssueInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IssueInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  
});
