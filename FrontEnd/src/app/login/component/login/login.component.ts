import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { User } from 'src/app/model/user/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  submitting = false;
  error = false;

  authentication;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient) {

  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required]]
    });
  }

  register() {
    this.router.navigate(['/register']);
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();
    this.loginForm.markAsDirty();

    if (!this.loginForm.valid) {
      window.alert('dsad');
      this.submitting = false;
      return;
    }

    const model = this.loginForm.value as User;

    this.http.post<any>('http://localhost:8000/authenticate',  model )
    .pipe(catchError((error: HttpErrorResponse) => {
      console.error(error);
      return of();
    })).subscribe(data => {
      if (data) {
        this.authentication = data;
        console.log(this.authentication);
      }
    });

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
