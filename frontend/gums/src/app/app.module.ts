import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {  FormsModule, ReactiveFormsModule } from '@angular/forms';
import {  HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpInterceptorAuthService } from './services/http-interceptor-auth.service';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './comp/user-login/user-login.component';
import { DashboardComponent } from './comp/dashboard/dashboard.component';
import {IonicModule} from '@ionic/angular';
import { SideBarComponent } from './comp/side-bar/side-bar.component';
import { AddFunctionComponent } from './comp/add-function/add-function.component';
import { ViewFunctionComponent } from './comp/view-function/view-function.component';
import { SettingComponent } from './comp/setting/setting.component';
import { EditFunctionComponent } from './comp/edit-function/edit-function.component';


// import { FontAwesomeModule } from '/@fortawesome/fontawesome-free\css\all.css';



@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    DashboardComponent,
    SideBarComponent,
    AddFunctionComponent,
    ViewFunctionComponent,
    SettingComponent,
    EditFunctionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    IonicModule,
    // MatIconModule

  ],
  providers: [
    {provide:HTTP_INTERCEPTORS,useClass:HttpInterceptorAuthService,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
