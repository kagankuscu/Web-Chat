import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams, HttpHeaders } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { of, Observable, Subject, BehaviorSubject } from 'rxjs';
import { AuthDataModel } from '../../models/auth-data.model';
import { LoginModel } from '../../models/login.model';
import { environment } from 'src/environments/environment';
import { AppMode } from 'src/environments/app-run-mode.enum';
import { TokenModel } from '../../models/token.model';
import { UserModel } from 'src/app/user/model/user.model';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authData: AuthDataModel = null;

  constructor(private http: HttpClient, private tokenService: TokenService) {

  }
  public login(userModel: LoginModel): Observable<boolean> {
    const url = `${environment.apiUrl}authenticate`;
    const userUrl = `${environment.apiUrl}api/users/g`;


    const authHeader = new HttpHeaders().append('DontAuthenticate', '');

    return this.http.post<TokenModel>(url, userModel, { headers: authHeader })
      .pipe(
        catchError(error => {
          if (error instanceof HttpErrorResponse) {
            this.showError(error.message);
            return of();
          }

          return null;
        }), map((data: TokenModel) => {
          if (data && data.token.length > 10) {
            this.authData = {} as AuthDataModel;
            this.tokenService.setToken(data.token);

            const params = new HttpParams().append('username', userModel.username);

            // this.http.get<UserModel>(userUrl, { params })
            //   .pipe(
            //     catchError((error: HttpErrorResponse) => {
            //       if (error instanceof HttpErrorResponse) {
            //         this.showError(error.message);
            //         return of();
            //       }

            //       return null;
            //     })).subscribe((user: UserModel) => {
            //       if (user) {
            //         this.authData.user = user;
            //       } else {
            //         this.authData.token = '';
            //         return false;
            //       }
            //     });

            if (environment.mode === AppMode.dev) {
              console.log(this.authData);
            }

            return true;
          } else {
            return false;
          }
        }));
  }

  public logout() {
    this.authData = {} as AuthDataModel;
    this.tokenService.clearToken();
    console.log('log out');
  }

  public getToken() {
    return this.authData.token;
  }

  private showError(errorText: string): void {
    window.alert(errorText);
  }

}
