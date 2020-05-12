import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
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


  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required]]
    });
  }

  register() {
  }

  onSubmit() {
    this.loginForm.markAllAsTouched();
    this.loginForm.markAsDirty();

    if (!this.loginForm.valid) {
      window.alert('dsad');
      this.submitting = false;
      return;
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
