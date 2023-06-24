import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../../authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouterActivateService implements CanActivate{

  constructor(private auth:AuthenticationService,
    private router:Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.auth.isLogin()){
      return true;
    }
    this.router.navigateByUrl("signIn");
    return false;
  }
}
