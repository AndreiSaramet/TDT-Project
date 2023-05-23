import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'mat-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css'],
  encapsulation: ViewEncapsulation.Emulated
})
export class StarRatingComponent implements OnInit {
  @Input('rating') rating: number = 3;
  @Input('starCount') starCount: number = 5;
  @Input('static') static: boolean = true
  @Output() ratingUpdated = new EventEmitter();

  snackBarDuration: number = 2000;
  ratingArr: Array<number> = [];

  constructor(private snackBar: MatSnackBar) {
  }


  ngOnInit() {
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
  }

  onClick(rating:number) {
    if(!this.static) {
      this.ratingUpdated.emit(rating);
    }
  }

  showIcon(index:number) {
    if (this.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }

}
export enum StarRatingColor {
  primary = "primary",
  accent = "accent",
  warn = "warn"
}
