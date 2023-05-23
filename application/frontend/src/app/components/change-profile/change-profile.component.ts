import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserDTO } from 'src/app/model/userDto.model';
import { userProfileDto } from 'src/app/model/userProfileDto.model';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-profile',
  templateUrl: './change-profile.component.html',
  styleUrls: ['./change-profile.component.css']
})
export class ChangeProfileComponent {
  bioFormControl = new FormControl('', [Validators.required, Validators.minLength(2)]);
  selectedFile: any = null;
  fileUpload: any = null;
  picture: any;
  userId: number = -1;

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

  constructor(private userService: UserService,
              private userProfileService: UserProfileService,
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
        this.getCurrentProfile();
      }
    }
  }

  getCurrentProfile(): void {
    this.userProfileService.findUserProfileByUserId(this.userId).subscribe(
      {
        next: (profile: userProfileDto) => {
          this.bioFormControl.setValue(profile.bio);
          //TODO: set current image from profile.profilePicture
        }
      });
  }

  changeProfile(): void {
    let formData = new FormData();
    console.log(this.selectedFile);
    if(this.selectedFile !== null) {
      formData.append('profilePicture',this.selectedFile);
    }
    formData.append('bio',this.bioFormControl.value!);
    this.userProfileService.addUserProfile(this.userId, formData)
            .subscribe({
                error: () => this.snackBar.open("Failed to change profile", "x"),
                next: () => {
                      this.snackBar.open("Changed profile", "x");
                      this.router.navigate(["/main"]);
                  }});

    }
  }
