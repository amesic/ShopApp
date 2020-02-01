import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

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

  errorMessageEmail = "";
  errorMessagePassword = "";
  errorMessageLastName = "";
  errorMessageFirstName = "";
  message;
  type;

  constructor(private userService: UserService) { }

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
    //valid everything
    if (this.email.valid &&
      this.password.valid &&
      this.firstname.valid &&
      this.lastname.valid) {
      this.userService.register(
        this.firstname.value + " " + this.lastname.value, 
        this.email.value,
        this.password.value).subscribe(user => {
          this.register.reset();
          this.type = "success";
          this.message = "You are successfully registered " + user.userName;
          window.scroll(0,0);
        }, err => {
          if (err.error == "User with this email already exist!" ||
          err.error == "You have already created an account with this email manually!") {
            this.message = "User with this email already exist!";
            this.type = "error";
            window.scroll(0,0);
          } else {
            console.log(err.error);
            this.message = "Something went wrong";
            this.type = "error";
            window.scroll(0,0);
          }
        });
    }
  }

}
