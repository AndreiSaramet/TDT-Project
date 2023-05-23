import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginDTO } from '../../model/loginDto.model';
import { TokenDTO } from '../../model/tokenDto.model';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  emailFormControl = new FormControl('', [Validators.required]);
  passwordFormControl = new FormControl('', [Validators.required]);

  constructor(private loginService: LoginService, 
              private snackBar: MatSnackBar,
              private jwtHelper: JwtHelperService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      const token = localStorage.getItem("token") as string;
      if (!this.jwtHelper.isTokenExpired(token)) {
        this.router.navigate(["/"]);
      }
    }
  }

  login(): void {
    const loginDto: LoginDTO = {
      username: this.emailFormControl.value!,
      password: this.passwordFormControl.value!
    }
    this.loginService.loginUser(loginDto)
            .subscribe({
                error: () => this.snackBar.open("Invalid login credentials!", "x"),
                next: (body) => {
                  this.snackBar.open("Logged in successfully!", "x");
                  const tokenDto = body as TokenDTO;
                  localStorage.setItem("token", tokenDto.token);
                  this.router.navigate(["/"]);
                }
            });
  }
}
