import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent implements OnInit {
  @Input() componentUsingSpinner;
  app = false;

  constructor() { }

  ngOnInit() {
  }
  ngOnChanges() {
    if (this.componentUsingSpinner == "app") {
      this.app = true;
    } 
  }

}
