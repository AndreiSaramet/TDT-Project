import {Component, OnInit} from '@angular/core';
import {ProductDTO} from "../../model/productDto.model";
import {ProductReviewDto} from "../../model/productReviewDto.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductsService} from "../../services/products.service";
import {ProductReviewsService} from "../../services/product-reviews.service";
import {UserService} from "../../services/user.service";
import {UserDTO} from "../../model/userDto.model";
import {UserProfileService} from "../../services/user-profile.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent implements OnInit {
  product: ProductDTO = {
    id: 1,
    name: "Cow",
    description: "Cattle (Bos taurus) are large, domesticated, " +
      "cloven-hooved, herbivores. They are a prominent modern member " +
      "of the subfamily Bovinae and the most widespread species of the " +
      "genus Bos. Adult females are referred to as cows and adult males " +
      "are referred to as bulls.",
    price: 100,
    category: "Animals",
    userId: 1
  }
  poster?: UserDTO;
  user_rating: number = 3;
  productImage: any;

  reviews: ProductReviewDto[] = [
    {
      id: 1,
      text: "very nice cow ",
      rating: 4,
      postDate: "22-01-2022",
      reviewerId: 1,
      productId: 1
    },{
      id: 1,
      text: "very satisfied with my cow, would 100% recommend",
      rating: 5,
      postDate: "22-01-2022",
      reviewerId: 1,
      productId: 1
    },{
      id: 1,
      text: "my cow came broken in the mail wouldn't recommend",
      rating: 2,
      postDate: "22-01-2022",
      reviewerId: 1,
      productId: 1
    },
  ]
  posterProfileImage: any;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private productReviewService: ProductReviewsService,
    private userService: UserService,
    private router: Router,
    private userProfileService: UserProfileService,
    private sanitizer: DomSanitizer,
  ) { }

  ngOnInit(): void {
    this.getProduct()
  }

  update_rating(rating: number) {
    this.user_rating = rating;
  }

  getProduct() {
    this.route.params.subscribe(
      (params) => this.productService.getProductById(params['id']).subscribe(
        (product) => {
          this.product = product
          this.loadProductPoster()
          this.loadProductImage()
          this.getProductReviews()
        }
      )
    )
  }

  getProductReviews() {
    this.productReviewService.getProductReviewsById(this.product.id!).subscribe(
      (reviews) => this.reviews = reviews
    );
  }

  onReviewAdded(review: ProductReviewDto) {
    review.reviewerId = Number(this.userService.getCurrentUserId())
    console.log(this.userService.getCurrentUserId())
    review.productId = this.product.id!;
    this.productReviewService.addProductReview(review).subscribe(() => this.getProductReviews());
  }

  loadProductPoster() {
    this.userService.findUserById(this.product.userId).subscribe(
      {
        next: (user) => {
          this.poster = user;
          this.loadUserProfileImage();
        }
      }
    );
  }
  
  loadProductImage() {
    this.productService.getProductImage(this.product.id!).subscribe(
      {
        next: (product) => {
          var objectURL = 'data:image/png;base64,' + product.image;
          this.productImage = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        }
      }
    );
  }

  loadUserProfileImage() {
    this.userProfileService.findUserProfileByUserId(this.poster!.id).subscribe(
      {
        next: (user) => {
          var objectURL = 'data:image/png;base64,' + user.profilePicture;
          this.posterProfileImage = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        }
      }
    );
  }

  goToAnotherUserProfile(){
    this.router.navigate(['/user'], {queryParams: {userId: this.poster?.id}});
  }
}
