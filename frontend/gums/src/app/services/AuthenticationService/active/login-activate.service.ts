import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../../authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginActivateService implements CanActivate{

  constructor(private auth:AuthenticationService,
    private router:Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.auth.isLogin()){
      this.router.navigateByUrl("dashboard");
      return false;
    }

    return true;
  }

}
