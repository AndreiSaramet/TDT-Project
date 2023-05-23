import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ProductReviewDto} from "../../../model/productReviewDto.model";
import {timestamp} from "rxjs";
import {formatDate} from "@angular/common";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {
  @Output('review_added') review_added = new EventEmitter<ProductReviewDto>();
  rating: number = 5;
  review_text: string = "";

  constructor() { }

  ngOnInit(): void {
  }

  onRatingChanged(rating: number) {
    this.rating = rating;
  }

  onReviewSubmit() {
    let now = new Date();
    this.review_added.emit(
      {
        id: 1,
        text: this.review_text,
        rating: this.rating,
        postDate: formatDate(now, "yyyy-MM-dd HH:mm:ss", "en-US"),
        reviewerId: 1,
        productId: 1
      }
    )
  }

}
