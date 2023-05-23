import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserDTO } from 'src/app/model/userDto.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-username',
  templateUrl: './change-username.component.html',
  styleUrls: ['./change-username.component.css']
})
export class ChangeUsernameComponent {
  usernameFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);

  userId: number = -1;
  constructor(private userService: UserService,
              private snackBar: MatSnackBar,
              private jwtHelper: JwtHelperService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      const token = localStorage.getItem("token") as string;
      if (this.jwtHelper.isTokenExpired(token)) {
        this.router.navigate(["/login"]);
      } else {
        this.userId = this.jwtHelper.decodeToken(token).id;
      }
    }
  }

  changeUsername(): void {
    this.userService.findUserById(this.userId).subscribe({
      next: (user: UserDTO) => {
        let newUser = user;
        newUser.username = this.usernameFormControl.value!;
        this.userService.updateUser(newUser).subscribe({
          next: () => {
            this.snackBar.open("Changed username", "x")
            this.router.navigate(["/main"]);
          }
        })
      }
    })
  }
}
