import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from "@angular/forms";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { BasicAuthHttpInterceptorService } from "./services/basic-auth-http-interceptor.service";


import { AppRoutingModule, LoginRegisterActivate } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SpinnerComponent } from './components/shared/spinner/spinner/spinner.component';
import { MessageComponent } from './components/shared/message/message.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SetProductsComponent } from './components/shared/set-products/set-products.component';
import { AddNewItemComponent } from './components/add-new-item/add-new-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    SpinnerComponent,
    MessageComponent,
    DashboardComponent,
    SetProductsComponent,
    AddNewItemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    HttpClientModule,
  ],
  providers: [
    LoginRegisterActivate,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHttpInterceptorService,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
