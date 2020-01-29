import { Component, OnInit } from '@angular/core';
import { FacebookService } from './services/facebook.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private facebookService: FacebookService) {}
  ngOnInit() {
    this.facebookService.connect();
  }
}
