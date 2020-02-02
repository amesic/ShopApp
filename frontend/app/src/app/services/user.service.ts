import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";
import { User } from "../models/User";
import { Observable } from "rxjs";
import { FacebookService } from './facebook.service';

@Injectable({
  providedIn: "root"
})
export class UserService {
  urlLogin = "/api/user/login";
  urlRegister = "/api/user/register";

  constructor(private http: HttpClient) {}

  authenticate(email: string, password: string) {
    return this.http
      .post<any>(this.urlLogin, { email, password })
      .pipe(
        map(userData => {
          sessionStorage.setItem("username", userData.username);
          let tokenStr = "Bearer " + userData.token;
          sessionStorage.setItem("token", tokenStr);
          sessionStorage.setItem("email", userData.email);
          sessionStorage.setItem("role", userData.role[0]);
          return userData;
        })
      );
  }
  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    return !(user == null);
  }
  logout() {
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("role");
  }
  getUserName() {
    return sessionStorage.getItem("username");
  }
  getUserEmail() {
    return sessionStorage.getItem("email");
  }
  getRole() {
    return sessionStorage.getItem("role");
  }
  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post<User>(this.urlRegister, {
      userName: name,
      email: email,
      password: password
    });
  }
}
