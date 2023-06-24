import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './comp/user-login/user-login.component';
import { DashboardComponent } from './comp/dashboard/dashboard.component';
import { LoginActivateService } from './services/AuthenticationService/active/login-activate.service';
import { RouterActivateService } from './services/AuthenticationService/active/router-activate.service';
import { AddFunctionComponent } from './comp/add-function/add-function.component';
import { ViewFunctionComponent } from './comp/view-function/view-function.component';
import { SettingComponent } from './comp/setting/setting.component';
import { EditFunctionComponent } from './comp/edit-function/edit-function.component';

const routes: Routes = [//editFunction
  {path:'editFunction/:id',component:EditFunctionComponent,canActivate:[RouterActivateService]},
  {path:'addFunction',component:AddFunctionComponent,canActivate:[RouterActivateService]},
  {path:'dashboard',component:DashboardComponent,canActivate:[RouterActivateService]},
  {path:'viewfunction/:id',component:ViewFunctionComponent,canActivate:[RouterActivateService]},
  {path:'login',component:UserLoginComponent,canActivate:[LoginActivateService]},
  {path:'setting',component:SettingComponent,canActivate:[RouterActivateService]},
  {path:'',redirectTo:'login',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
