import { Component, OnInit, Input } from '@angular/core';
import { faCheckCircle } from "@fortawesome/free-solid-svg-icons";
import { faTimesCircle } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  @Input() message;
  @Input() type;
  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;

  constructor() { }

  ngOnInit() {
  }

}
