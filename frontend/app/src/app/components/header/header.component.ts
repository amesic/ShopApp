import { Component, OnInit, OnChanges, NgZone } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "src/app/services/user.service";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";
import { FacebookService } from 'src/app/services/facebook.service';

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  faPlusCircle = faPlusCircle;

  constructor(public userService: UserService,
    public router: Router,
    public facebookService: FacebookService) {}

  ngOnInit() {}

  logout() {
    this.userService.logout();
    if (this.facebookService.isUserLogged()) {
      this.facebookService.logout();
    }
  }
}
