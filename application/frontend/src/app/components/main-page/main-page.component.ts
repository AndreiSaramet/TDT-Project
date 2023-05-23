import { animate, query, stagger, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserDTO } from 'src/app/model/userDto.model';
import { userProfileDto } from 'src/app/model/userProfileDto.model';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserService } from 'src/app/services/user.service';
import { ProductsService } from "../../services/products.service";
import { Product } from 'src/app/model/Product';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  animations: [
    trigger('fadeIn', [
      transition('void => true', [
        query('*', [
          style({ opacity: 0 }),
          stagger(10, [
            animate('0.35s', style({ opacity: 1 }))
          ])
        ], { optional: true })
      ])
    ])
  ],
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  products: Product[] = [];
  user!: userProfileDto;
  currentUser!: UserDTO;
  image: any;
  bio!: string;
  fadeIn = false;
  userId: any;
  images: any[] = [];
  friends: UserDTO[] = [];
  friendsImages: any[] = [];

  constructor(
    private userProfileService: UserProfileService,
    private productService: ProductsService,
    private userService: UserService,
    private jwtHelper: JwtHelperService,
    private router: Router,
    private sanitizer: DomSanitizer
  ){ }

  ngOnInit(): void {
    const token = localStorage.getItem("token") as string;

    if (token === null || token === undefined) {
      this.router.navigate(["/login"]);
    }
    
    if (this.jwtHelper.isTokenExpired(token)) {
      this.router.navigate(["/login"]);
    }

    const id = this.jwtHelper.decodeToken(token)['id'];
    if (id === undefined) {
      this.router.navigate(["/login"]);
    }

    this.userId = id;
    this.userService.findUserById(id).subscribe(
      (userDto: UserDTO) => {
        this.currentUser = userDto;
        this.getCurrentUserProfileById(userDto.id);
      }
    );

    this.loadProducts();
    this.loadFriends();
    this.fadeIn = true;
  }

  private getCurrentUserProfileById(userId: number) {
    this.userProfileService.findUserProfileByUserId(userId).subscribe(
      user => {
       this.user = user;
       let objectURL = 'data:image/png;base64,' + user.profilePicture;
       this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
       this.bio = user.bio;
     }
    );
  }

  private loadFriendsProfiles(): void {
    for (let friend of this.friends) {
      this.loadFriendProfile(friend.id);
    }
  }

  private loadFriendProfile(friendId: number): any {
    this.userProfileService.findUserProfileByUserId(friendId).subscribe(
      user => {
        var objectURL = 'data:image/png;base64,' + user.profilePicture;
        var image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        this.friendsImages.push(image);
     }
    );
  }

  goToAdd() {
    this.router.navigate(["/addProduct"]);
  }

  private loadProducts() {
    this.productService.getAllProductsByUserId(this.userId).subscribe(
      (products: Product[]) => this.products = products
    )
  }

  private loadFriends() {
    this.userService.getUserFriends(this.userId).subscribe(
      (friends: UserDTO[]) => {
        this.friends = friends
        this.loadFriendsProfiles();
      }
    )
  }

  deleteProduct(productId: number) {
    this.productService.deleteProduct(productId).subscribe(
      {
        next: () => {
          window.location.reload();
        }
      }
    )
  }

  goToEdit(productId: number) {
    this.router.navigate(["/editProduct"], {queryParams: {productId: productId}});
  }

  goToProductPage(productId: number) {
    this.router.navigate([`/product/${productId}`]);
  }

  goToFriend(userId: number) {
    this.router.navigate(['/user'], {queryParams: {userId: userId}});
  }

  goToChangeUsername() {
    this.router.navigate(['/username/change']);
  }

  goToChangeProfile() {
    this.router.navigate(['/profile/change']);
  }

  goToFriendRequests() {
    this.router.navigate(['/friend-requests/received']);
  }

  // used by visual components
  private friendCount(): number {
    return this.friends.length;
  }

  public hasFriends(): boolean {
    return this.friendCount() > 0;
  }

  private productCount(): number {
    return this.products.length;
  }

  public hasProducts(): boolean {
    return this.productCount() > 0;
  }
}
