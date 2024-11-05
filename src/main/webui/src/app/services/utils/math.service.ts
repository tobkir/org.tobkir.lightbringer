import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MathService {

  constructor() { }

  round(num: number, fractionDigits: number): number {
    return Number(num.toFixed(fractionDigits));
  }
}
