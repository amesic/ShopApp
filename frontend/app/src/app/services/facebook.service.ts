import { Injectable, NgZone } from '@angular/core';
import { User } from '../models/user';
declare var FB: any;

@Injectable({
  providedIn: 'root'
})
export class FacebookService {
  userLogged;

  constructor(private _zone: NgZone) { }

  checkLoginState() {
    FB.getLoginStatus(function(response) {
      if (response.status == 'connected') {
        return true;
    }
  });
  return false;
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
       js.src = "https://connect.facebook.net/en_US/sdk.js";
       fjs.parentNode.insertBefore(js, fjs);
     }(document, 'script', 'facebook-jssdk'));
  }

  login() {
    if (!this.checkLoginState()) {
       FB.login((response :any) => {
        this._zone.run(() => {
         if (response.status === 'connected') {
           // Logged into your webpage and Facebook, get informations
           FB.api('/me?fields=name,email', (response :any) => {
            this._zone.run(() => {
            if (response && !response.error) {
              // informations of logged user
             
            } else {
              // something went wrong
            }
          })
        })
           this.userLogged = true;
         } else {
           // The person is not logged into your webpage or we are unable to tell. 
         }
        })
       }, {scope: 'public_profile,email'});
     }
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
