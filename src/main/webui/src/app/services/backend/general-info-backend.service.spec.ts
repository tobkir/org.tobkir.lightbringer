import { TestBed } from '@angular/core/testing';

import { GeneralInfoBackendService } from './general-info-backend.service';

describe('GeneralInfoBackendService', () => {
  let service: GeneralInfoBackendService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneralInfoBackendService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
