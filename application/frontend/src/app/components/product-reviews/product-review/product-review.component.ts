import {Component, Input, OnInit} from '@angular/core';
import {ProductReviewDto} from "../../../model/productReviewDto.model";
import {UserDTO} from "../../../model/userDto.model";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-product-review',
  templateUrl: './product-review.component.html',
  styleUrls: ['./product-review.component.css']
})
export class ProductReviewComponent implements OnInit {
  @Input('product_review') product_review: ProductReviewDto | undefined;
  reviewer: UserDTO | undefined;

  constructor(
    private usersService: UserService
  ) {
  }

  ngOnInit(): void {
    this.getReviewer()
  }

  getReviewer() {
    let id = this.product_review ? this.product_review.reviewerId : 0
    this.usersService.findUserById(id).subscribe((user) => this.reviewer = user)
  }
}
