import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductReviewDto} from "../../model/productReviewDto.model";

@Component({
  selector: 'app-product-reviews',
  templateUrl: './product-reviews.component.html',
  styleUrls: ['./product-reviews.component.css']
})
export class ProductReviewsComponent implements OnInit {
  @Input('product-reviews') product_reviews: ProductReviewDto[] = []
  @Output('review_added') review_added = new EventEmitter<ProductReviewDto>();

  constructor() { }

  ngOnInit(): void {
  }

  onReviewAdded(review: ProductReviewDto) {
    this.review_added.emit(review);
  }
}

