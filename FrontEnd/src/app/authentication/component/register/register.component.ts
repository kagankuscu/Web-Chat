import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  loginForm: FormGroup;

  submitting = false;
  error = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
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

  cancel() {
    this.router.navigate(['/login']);
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
