import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { BehaviorSubject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { LoginModel } from '../../models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  public loginForm: FormGroup;
  public submitting = false;
  public error = '';

  public tokenValue = '';
  private unSubscribeSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthenticationService) {
    // this.authService.tokenObservable$.pipe(takeUntil(this.unSubscribeSubject)).subscribe(value => this.tokenValue = value);
    this.authService.tokenObservable$.subscribe(s => this.tokenValue = s);
  }

  ngOnInit(): void {

    console.log('token value: ' + this.tokenValue + 'token:' + this.authService.tokenObservable$);

    if (this.tokenValue !== '') {
      this.router.navigate(['chat']);
    }

    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required]]
    });
  }

  ngOnDestroy(): void {
    this.unSubscribeSubject.next(true);
    this.unSubscribeSubject.complete();
  }

  register() {
    this.router.navigate(['auth/register']);
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();
    this.loginForm.markAsDirty();

    if (this.loginForm.valid) {
      this.submitting = true;

      const model = (this.loginForm.value as LoginModel);

      const result = this.authService.login(model);
      result.subscribe(value => {
        if (value) {
          this.router.navigate(['chat']);
        } else {
          this.submitting = false;
          this.error = 'Username or password is invalid!';
        }
      });
    } else {
      this.error = 'Please fill the username and password fields!';
    }
  }

  getClass(controlName: string) {
    const isValid = this.loginForm.controls[controlName].valid;

    if (!isValid && this.loginForm.controls[controlName].touched) {
      return 'not-valid';
    } else {
      return '';
    }
  }
}
