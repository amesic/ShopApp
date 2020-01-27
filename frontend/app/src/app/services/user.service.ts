import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class UserService {
  urlLogin = "/api/user/login";

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
  logOut() {
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
}
