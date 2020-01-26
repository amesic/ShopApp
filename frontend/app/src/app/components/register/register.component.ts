import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register = new FormGroup({
    firstname: new FormControl("", Validators.required),
    lastname: new FormControl("", Validators.required),
    email: new FormControl("", Validators.required),
    password: new FormControl("", Validators.compose([Validators.required, Validators.minLength(8)]))
  });

  errorMessageEmail;
  errorMessagePassword;
  errorMessageLastName;
  errorMessageFirstName;

  constructor() { }

  ngOnInit() {
  }

  get email() {
    return this.register.get("email");
  }
  get password() {
    return this.register.get("password");
  }
  get lastname() {
    return this.register.get("firstname");
  }
  get firstname() {
    return this.register.get("lastname");
  }

  submit() {
    //first name errors
    if (this.firstname.errors != null) {
      this.errorMessageFirstName = "First name is required!";
    } else {
      this.errorMessageFirstName = "";
    }
    //last name errors
    if (this.lastname.errors != null) {
      this.errorMessageLastName = "Last name is required!";
    } else {
      this.errorMessageLastName = "";
    }
    //email errors
    if (this.email.errors != null) {
      this.errorMessageEmail = "Email is required!";
    } else {
      this.errorMessageEmail = "";
    }
    //password errors
    if (this.password.errors != null && this.password.errors.required) {
      this.errorMessagePassword = "Password is required!";
    } else if (!this.password.valid) {
      this.errorMessagePassword = "Password must be minimum 8 character!";
    } else {
      this.errorMessagePassword = "";
    }
  }

}
