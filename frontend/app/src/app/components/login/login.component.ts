import { Component, OnInit } from "@angular/core";
import { FacebookService } from "src/app/services/facebook.service";
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { faFacebookSquare } from "@fortawesome/free-brands-svg-icons";
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {

  login = new FormGroup({
    email: new FormControl("", Validators.required),
    password: new FormControl("", Validators.compose([Validators.required, Validators.minLength(8)]))
  });

  faFacebookSquare = faFacebookSquare;

  errorMessageEmail;
  errorMessagePassword;
  message;

  informationFromFB;

  constructor(
    private facebookService: FacebookService,
    private userService: UserService,
    private router: Router) {}

  ngOnInit() {}

  get email() {
    return this.login.get("email");
  }
  get password() {
    return this.login.get("password");
  }

  loginWithFacebook() {
  this.facebookService.login();
  }
  submit() {
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
    //valid both
    if (this.email.valid && this.password.valid) {
      this.userService
        .authenticate(
          this.login.get("email").value,
          this.login.get("password").value
        )
        .subscribe(
          userData => {
            console.log(userData);
            this.router.navigate(["/"]);
          },
          err => {
            console.log(err)
            this.message = "Oops, that's not a match. Check your email/password!";
          }
        );
    }
  }
}
