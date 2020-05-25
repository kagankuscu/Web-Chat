import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
  HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { TokenModel } from '../models/token.model';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private tokenData: TokenModel = { token: 'null' };

  constructor(private authService: AuthenticationService, private router: Router) {
    this.authService.tokenObservable.subscribe(s => {
      this.tokenData.token = s;
      console.log(this.tokenData);
    }
    );
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
      'Access-Control-Allow-Headers': 'X-Requested-With,content-type',
      'Access-Control-Allow-Credentials': 'true'
    });

    // const tokenData = this.authService.getToken();

    if (!request.headers.has('DontAuthenticate') && this.tokenData) {
      headers = headers.append('Authorization', 'Bearer ' + this.tokenData);
    }

    const modifiedRequest = request.clone({
      headers
    });

    return next.handle(modifiedRequest)
      .pipe(
        tap(event => {
          if (event instanceof HttpResponse) {
            if (event.status === 401) {
              console.log(event);
              this.router.navigate(['/auth/login']);
            }
          }
        })
      );
  }
}
