import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QueryParamDateService {

  getDateQueryParamString(date: Date) {
    let month;
    let hours;
    let minutes;
    let seconds;
    let milliseconds;
    let day;

    if (date.getMonth() <= 8) {
      month = "0".concat(String((date.getMonth() + 1)));
    } else {
      month = (date.getMonth() + 1).toString()
    }

    if (date.getDate() <= 8) {
      day = "0".concat(String(date.getDate()));
    } else {
      day = date.getDate().toString();
    }

    if (date.getHours().toString().length == 1) {
      hours = "0" + date.getHours();
    } else {
      hours = date.getHours().toString();
    }

    if (date.getMinutes().toString().length == 1) {
      minutes = "0" + date.getMinutes();
    } else {
      minutes = date.getMinutes().toString();
    }

    if (date.getSeconds().toString().length == 1) {
      seconds = "0" + date.getSeconds();
    } else {
      seconds = date.getSeconds().toString();
    }

    if (date.getMilliseconds().toString().length == 1) {
      milliseconds = "00" + date.getMilliseconds();
    } else if(date.getMilliseconds().toString().length == 2){
      milliseconds = "0"+date.getSeconds().toString();
    } else {
      milliseconds = date.getMilliseconds().toString();
    }

    return date.getFullYear().toString()
      .concat("-")
      .concat(month)
      .concat("-")
      .concat(day)
      .concat(" ")
      .concat(hours)
      .concat(":")
      .concat(minutes)
      .concat(":")
      .concat(seconds)
      .concat(" ")
      .concat(milliseconds)
      ;
  }
}
