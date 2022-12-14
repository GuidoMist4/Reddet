import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {SignupRequestPayload} from './signup-request.payload';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup
  signupRequestPayload: SignupRequestPayload

  constructor() {
    this.signupRequestPayload = {username: '', password: '', email: ''};
  }


  ngOnInit() {
    this.signUpForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    })
  }

  signup() {
    this.signupRequestPayload.username = this.signUpForm.get('username')?.value;
    this.signupRequestPayload.password = this.signUpForm.get('password')?.value;
    this.signupRequestPayload.email = this.signUpForm.get('email')?.value;
  }

}