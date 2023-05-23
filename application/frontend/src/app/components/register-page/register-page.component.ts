import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { RegisterDTO } from '../../model/registerDto.model';
import { RegisterService } from '../../services/register.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ActivatedRoute, Router } from '@angular/router';
import { UserProfileService } from '../../services/user-profile.service';
import { UserDTO } from '../../model/userDto.model';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  usernameFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  passwordFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  bioFormControl = new FormControl('', [Validators.required, Validators.minLength(2)]);
  selectedFile: any = null;
  fileUpload: any = null;
  picture: any;

  onFileSelected(event: any): void {
      this.selectedFile = event.target.files[0] ?? null;
  }

  changedPicture(event:any) {
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.selectedFile = event.target.files[0];
    }
    this.readURL(event);
  }

  readURL(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0];

      const reader = new FileReader();
      reader.onload = e => this.picture = reader.result;

      reader.readAsDataURL(file);
    }
  }

  constructor(private registerService: RegisterService,
              private userProfileService: UserProfileService,
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

  register(): void {
    const registerDto: RegisterDTO = {
      username: this.usernameFormControl.value!,
      email: this.emailFormControl.value!,
      password: this.passwordFormControl.value!,
      role: "USER"
    }
    let formData = new FormData();
    formData.append('profilePicture',this.selectedFile);
    if(this.selectedFile == null) {
      this.snackBar.open("Please select a profile picture", "OK", {duration: 2000});
      return;
    }
    formData.append('bio',this.bioFormControl.value!);
    this.registerService.registerUser(registerDto)
            .subscribe({
                error: () => this.snackBar.open("Failed to register", "x"),
                next: (user: UserDTO) => {
                  this.userProfileService.addUserProfile(user.id, formData).subscribe({
                    next: () => {
                      this.snackBar.open("Registered successfully!", "x");
                      this.router.navigate(["/login"]);
                    }
                  });

                }
            });
  }
}
