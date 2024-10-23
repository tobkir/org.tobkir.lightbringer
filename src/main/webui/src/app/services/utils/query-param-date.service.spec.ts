import { TestBed } from '@angular/core/testing';

import { QueryParamDateService } from './query-param-date.service';

describe('QueryParamDateService', () => {
  let service: QueryParamDateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QueryParamDateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
