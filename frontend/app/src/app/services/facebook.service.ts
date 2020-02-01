import { Injectable, NgZone } from '@angular/core';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
declare var FB: any;

@Injectable({
  providedIn: 'root'
})
export class FacebookService {
  userLogged;
  loading = false;
  message;
  private subject = new Subject<any>();

  constructor(
    private _zone: NgZone,
    private userService: UserService,
    private router: Router) { }

  checkLoginState() {
    FB.getLoginStatus(function(response) {
      if (response.status == 'connected') {
        return true;
    }
  });
  return false;
}
requestIsDone() {
  return this.loading;
}
getMessage() {
  return this.message;
}

  connect() {
    (window as any).fbAsyncInit = function() {
      FB.init({
        appId      : '162706665056449',
        cookie     : true,
        xfbml      : true,
        version    : 'v5.0'
      });
    };
    (function(d, s, id){
       var js, fjs = d.getElementsByTagName(s)[0];
       if (d.getElementById(id)) {return;}
       js = d.createElement(s); js.id = id;
       js.src = "https://connect.facebook.net/en_US/all.js";
       fjs.parentNode.insertBefore(js, fjs);
     }(document, 'script', 'facebook-jssdk'));
  }

  login(): Observable<any>{
    if (!this.checkLoginState()) {
       FB.login((response :any) => {
        this._zone.run(() => {
         if (response.status === 'connected') {
           // Logged into your webpage and Facebook, get informations
           FB.api('/me?fields=name,email', (response :any) => {
            this._zone.run(() => {
            if (response && !response.error) {
              // informations of logged user
              this.loading = true;
             this.userService.register(response.name, response.email, null)
              .subscribe(user => {
                this.userService.authenticate(user.email, "")
                .subscribe(user => {
                  this.loading = false;
                  this.router.navigate(["/"]);
                  this.subject.next({ text: undefined });
                  return this.subject.asObservable();
                }, err => {
                  this.loading = false;
                  console.log("login", err.error);
                  this.subject.next({ text: undefined });
                  return this.subject.asObservable();
                });
              }, err => {
                if (err.error == "User with this email already exist!") {
                this.userService.authenticate(response.email, "")
                .subscribe(user => {
                  this.loading = false;
                  this.router.navigate(["/"]);
                  this.subject.next({ text: undefined });
                  return this.subject.asObservable();
                }); 
              } else if (err.error == "You have already created an account with this email manually!") {
                this.loading = false;
                this.message = err.error;
                this.subject.next({ text: this.message });
                return this.subject.asObservable();
              } else {
                this.loading = false;
                console.log("register",err.error);
                this.subject.next({ text: undefined });
                return this.subject.asObservable();
              }
              });
             
            } else {
              // something went wrong
             this.subject.next({ text: "Something went wrong with FB api" });
             return this.subject.asObservable();
            }
          })
        })
           this.userLogged = true;
         } else {
           this.userLogged = false;
           // The person is not logged into your webpage or we are unable to tell. 
           this.subject.next({ text: "Something went wrong with FB login" });
           return this.subject.asObservable();
         }
        })
       }, {scope: 'public_profile,email'});
     }
     this.subject.next({ text: undefined });
     return this.subject.asObservable();
 }

 logout() {
  FB.logout((response) => {
    this._zone.run(() => {
      // Person is now logged out
      this.userLogged = false;
    })
 });
}

  isUserLogged() {
    return this.userLogged;
  }
}
