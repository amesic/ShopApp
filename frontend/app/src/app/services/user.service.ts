import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";
import { User } from '../models/user';
import { Observable } from 'rxjs';

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
          sessionStorage.setItem("email", email);
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
  }
  getUserName() {
    if (this.isUserLoggedIn()) {
      return sessionStorage.getItem("username");
    } else {
      return "";
    }
  }
  getUserEmail() {
    if (this.isUserLoggedIn()) {
      return sessionStorage.getItem("email");
    } else {
      return "";
    }
  }
  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post<User>(this.urlRegister, {
      userName: name,
      email: email,
      password: password
    });
  }
}
