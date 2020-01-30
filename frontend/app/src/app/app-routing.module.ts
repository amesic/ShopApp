import { NgModule, Injectable } from "@angular/core";
import {
  Routes,
  RouterModule,
  CanActivate,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from "@angular/router";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { UserService } from "./services/user.service";
import { DashboardComponent } from "./components/dashboard/dashboard.component";

@Injectable()
export class LoginRegisterActivate implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.userService.isUserLoggedIn()) {
      this.router.navigate(["/"]);
    }
    return true;
  }
}

const routes: Routes = [
  { path: "", redirectTo: "/home", pathMatch: "full" },
  {
    path: "home",
    children: [
      {
        path: "login",
        component: LoginComponent,
        canActivate: [LoginRegisterActivate]
      },
      {
        path: "register",
        component: RegisterComponent,
        canActivate: [LoginRegisterActivate]
      },
      { path: "", component: DashboardComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
