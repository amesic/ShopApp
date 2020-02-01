import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHttpInterceptorService {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const request = req.clone({
    url: "http://localhost:8080" + req.url,
    //url: "https://shopwisely-api.herokuapp.com" + req.url,
    headers: req.headers.set("Authorization", sessionStorage.getItem("token") || ""),
    withCredentials: true
  });
  return next.handle(request);
}
}
