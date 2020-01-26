import { Component, OnInit, OnChanges, NgZone } from '@angular/core';
import { checkLoginState } from '../../utils/checkLoginState';
import { Router } from '@angular/router';
import { FacebookService } from 'src/app/services/facebook.service';
declare var FB: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
 private userLogged;

  constructor(private router: Router, private facebookService: FacebookService) { }

  ngOnInit() {
    this.facebookService.connect();
  }

  logout() {
    this.facebookService.logout();
  }

}
