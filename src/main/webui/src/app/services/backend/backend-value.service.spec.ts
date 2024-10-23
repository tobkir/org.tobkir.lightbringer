import { TestBed } from '@angular/core/testing';

import { BackendValueService } from './backend-value.service';

describe('BackendValueService', () => {
  let service: BackendValueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BackendValueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
