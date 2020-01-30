import { Component, OnInit, OnChanges, NgZone } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "src/app/services/user.service";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  faPlusCircle = faPlusCircle;

  constructor(private userService: UserService,
    private router: Router) {}

  ngOnInit() {}

  logout() {
    this.userService.logout();
  }
}
