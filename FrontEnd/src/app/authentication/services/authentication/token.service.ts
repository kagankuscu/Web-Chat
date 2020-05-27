import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private tokenSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);

  constructor() {
   }

  setToken(s: string) {
    if (s) {
      this.tokenSubject.next(s);
    }
  }

  getToken() {
    return this.tokenSubject.asObservable();
  }

  clearToken() {
    this.tokenSubject.next(null);
  }
}
