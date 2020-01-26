import { Component, OnInit } from "@angular/core";
import { FacebookService } from "src/app/services/facebook.service";
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { faFacebookSquare } from "@fortawesome/free-brands-svg-icons";

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
  constructor(private facebookService: FacebookService) {}

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
  }
}
