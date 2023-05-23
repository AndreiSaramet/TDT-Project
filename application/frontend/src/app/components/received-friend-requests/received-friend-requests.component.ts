import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { FriendRequestDTO } from 'src/app/model/friendRequestDto.model';
import { UserDTO } from 'src/app/model/userDto.model';
import { userProfileDto } from 'src/app/model/userProfileDto.model';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-received-friend-requests',
  templateUrl: './received-friend-requests.component.html',
  styleUrls: ['./received-friend-requests.component.css']
})
export class ReceivedFriendRequestsComponent {
  userId: any;
  friends: any[] = [];
  friendsImages: any[] = [];
  
  constructor(
    private userProfileService: UserProfileService,
    private userService: UserService,
    private jwtHelper: JwtHelperService,
    private router: Router,
    private sanitizer: DomSanitizer
  ){ }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      const token = localStorage.getItem("token") as string;
      if (this.jwtHelper.isTokenExpired(token)) {
        this.router.navigate(["/login"]);
      } else {
        this.userId = this.jwtHelper.decodeToken(token).id;
        this.loadFriendRequests();
      }
    }
  }

  goToProfile(friendId: number) {
    this.router.navigate([`/user`], {queryParams: {userId: friendId}});
  }

  loadFriendRequests() {
      this.userService.findAllFriendRequestsByReceiver(this.userId).subscribe({
        next: (requests: FriendRequestDTO[]) => {
          requests.forEach(request => {
            this.userProfileService.findUserProfileByUserId(request.senderId).subscribe({
              next: (userProfile: userProfileDto) => {
                var objectURL = 'data:image/png;base64,' + userProfile.profilePicture;
                var image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
                this.friendsImages.push(image);
              }
            });
            this.userService.findUserById(request.senderId).subscribe({
              next: (user: UserDTO) => {
                this.friends.push(user);
              }
            });
          })
        }
      })
  }
}
