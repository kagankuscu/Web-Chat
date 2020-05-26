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
import { TokenService } from '../services/authentication/token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService, private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
      'Access-Control-Allow-Headers': 'X-Requested-With,content-type',
      'Access-Control-Allow-Credentials': 'true'
    });

    let tokenData;
    this.tokenService.getToken().subscribe(value => tokenData = value);

    if (!request.headers.has('DontAuthenticate') && tokenData) {
      headers = headers.append('Authorization', 'Bearer ' + tokenData);
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
