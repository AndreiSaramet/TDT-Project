import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ProductsService } from 'src/app/services/products.service';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { UserService } from 'src/app/services/user.service';
import { Product } from '../../model/Product';

@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {

  @Input() public product: Product | null = null
  public reviewCount: number | null = null
  public image: SafeUrl | null = null
  profileImage: any;

  constructor(
    private productsService: ProductsService,
    private userProfileService: UserProfileService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.setReviewsCount()
    this.setImage()
    this.loadImage()
  }

  public setReviewsCount() {
    if (this.product != null && this.product.id != null) {
      this.productsService.getProductReviewsCount(this.product.id).subscribe(
        (receivedCount) => { this.reviewCount = receivedCount }
      )
    }
  }

  public setImage() {
    if (this.product != null && this.product.id != null) {
      this.productsService.getProductImage(this.product.id).subscribe(
        (receivedImage) => {
          if (receivedImage === null) {
            this.image = null
          } else {
            let imageURL = "data:image/png;base64," + receivedImage.image;
            this.image = this.sanitizer.bypassSecurityTrustUrl(imageURL);
          }
        }
      )
    }
  }

  public loadImage() {
    this.userProfileService.findUserProfileByUserId(this.product?.userId!).subscribe(
      {
        next: (userProfile) => {
          var objectURL = 'data:image/png;base64,' + userProfile.profilePicture;
          this.profileImage = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        }
      }
    );
  }


  public hasReviews() {
    return this.reviewCount != null && this.reviewCount > 0
  }

  public showReviewCount() {
    if (this.hasReviews()) {
      if (this.reviewCount === 1) {
        return `${this.reviewCount} review`
      }
      return `${this.reviewCount} reviews`
    }
    return "No reviews"
  }

  public getProductId() {
    return this.product ? this.product.id : 0;
  }
}
