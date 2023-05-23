import { animate, query, stagger, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/Product';
import { ProductDTO } from 'src/app/model/productDto.model';
import { UserDTO } from 'src/app/model/userDto.model';
import { userProfileDto } from 'src/app/model/userProfileDto.model';
import { ProductReviewsService } from 'src/app/services/product-reviews.service';
import { ProductsService } from 'src/app/services/products.service';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  animations: [
    trigger('moveIn', [
      transition('void => true', [
        query('*', [
          style({ transform: 'translateX(200%)' }),
          stagger(5, [
            animate('0.3s ease-in', style({ transform: 'translateX(0%)' }))
          ])
        ], { optional: true })
      ])
    ])
  ],
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private productReviewService: ProductReviewsService,
    private userService: UserService,
    private router: Router,
    private userProfileService: UserProfileService,
    private sanitizer: DomSanitizer,) { }

  moveIn = false;
  products: Product[] = [];
  user?: UserDTO;
  image: any;
  userProfile?: userProfileDto;
  isFriend: boolean = false;
  currentUserId?: number;
  userId?: number;
  receivedFriendRequest: boolean = false;
  sentFriendRequest: boolean = false;

  ngOnInit(): void {
    this.moveIn = true;

    this.getUser();
  }

  getUser() {
    this.userId = this.route.snapshot.queryParams['userId']
    this.userService.findUserById(this.userId!).subscribe(
      (user) => {
        this.user = user
        this.userProfileService.findUserProfileByUserId(this.userId!).subscribe(
          (profile) => {
            this.userProfile = profile
            let objectURL = 'data:image/png;base64,' + profile.profilePicture;
            this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
            this.getProducts(this.userId!);
            this.loadFriendshipStatus(this.userId!);
          }
        )
      }
    )
  }

  getProducts(userId: number) {
    this.productService.getAllProductsByUserId(userId).subscribe(
      (foundProducts) => {
        this.products = foundProducts
      }
    )
  }

  sendFriendRequest() {
      this.userService.sendFriendRequest(this.currentUserId!, this.userId!).subscribe(
        {
          next: () => window.location.reload(),
          error: () => window.location.reload(),
        }
      )
  }

  removeFriend() {
    this.userService.removeFriend(this.currentUserId!, this.userId!).subscribe(
      {
        next: () => window.location.reload(),
        error: () => window.location.reload(),
      }
    )
  }

  acceptFriendRequest() {
    this.userService.acceptFriendRequest(this.userId!, this.currentUserId!).subscribe(
      {
        next: () => window.location.reload(),
        error: () => window.location.reload(),
      }
    )
  }

  denyFriendRequest() {
    this.userService.removeFriendRequest(this.userId!, this.currentUserId!).subscribe(
      {
        next: () => window.location.reload(),
        error: () => window.location.reload(),
      }
    )
  }

  cancelFriendRequest() {
    this.userService.removeFriendRequest(this.currentUserId!, this.userId!).subscribe(
      {
        next: () => window.location.reload(),
        error: () => window.location.reload(),
      }
    )
  }

  loadFriendshipStatus(userId: number) {
    this.currentUserId = Number(this.userService.getCurrentUserId());
    if (this.userId == this.currentUserId) {
      this.router.navigate(["/main"]);     
    }
    this.userService.areUsersFriends(this.currentUserId, userId).subscribe(
      (areFriends) => {
        this.isFriend = areFriends;
        if (areFriends === false) {
            this.userService.findFriendRequestBySenderAndReceiver(userId, this.currentUserId!).subscribe(
              {
                error: () => this.receivedFriendRequest = false,
                next: () => this.receivedFriendRequest = true
              }
            )
            this.userService.findFriendRequestBySenderAndReceiver(this.currentUserId!, userId).subscribe(
              {
                error: () => this.sentFriendRequest = false,
                next: () => this.sentFriendRequest = true
              }
            )
        }
      }
    )
  }
  
}
