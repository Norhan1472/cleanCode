
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  user:User=new User();
  signUpParentForm! : FormGroup;
  signInParentForm! : FormGroup;

  constructor(private signUpChildForm:FormBuilder,
              private signInChildForm:FormBuilder,
              private auth:AuthenticationService,
              private router:Router,
              private renderer: Renderer2,
                 private el: ElementRef) { }


  ngOnInit(): void {
    if (!this.auth) {
      console.error('AuthenticationService is null or undefined');
      return;
    }
    const sign_in_btn = this.el.nativeElement.querySelector("#sign-in-btn") as HTMLButtonElement;
    const sign_up_btn = this.el.nativeElement.querySelector("#sign-up-btn") as HTMLButtonElement;
    const container = this.el.nativeElement.querySelector(".container") as HTMLElement;

    sign_up_btn.addEventListener("click", () => {
      this.renderer.addClass(container, "sign-up-mode");
    });

    sign_in_btn.addEventListener("click", () => {
      this.renderer.removeClass(container, "sign-up-mode");
    });
    this.signIn();
    this.signUp();

  }
  signUp(){
    this.signUpParentForm=this.signUpChildForm.group({
      user : this.signUpChildForm.group({
          email:[''],
          password:['']
      })
    }
    )
  }
  submitSignUp(){
    console.log("gggggggggg");
    this.auth.signUp(this.signUpParentForm.controls['user'].value.email,
    this.signUpParentForm.controls['user'].value.password).subscribe(
      response=>{

        console.log(response);
        this.goToLogin();

      },
      error=>{
        console.log(error);
      }
    )

  }
  signIn(){
    this.signInParentForm=this.signInChildForm.group({
      userSignIn : this.signInChildForm.group({
          email:[''],
          password:['']
      })
    }
    )
  }
  userlogin()
  {
    const password = this.signInParentForm.controls['userSignIn'].value.password;
    const userName = this.signInParentForm.controls['userSignIn'].value.email;
    this.auth.signIn(userName,password).subscribe(
      response=>{
        console.log(response);
        this.goToDashBoard()
      },
      error=>{
        console.log(error);
      }
    )

  }
  goToDashBoard(){
    this.router.navigate(["/dashboard"]);
  }

  goToLogin(){
    this.ngOnInit();
    this.router.navigate(["/login"]);
    console.log("lllll");
  }

}


