import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ProductReviewDto} from "../model/productReviewDto.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductReviewsService {
  private baseUrl: string = "http://localhost:8080"

  constructor(private http: HttpClient) {
  }

  getProductReviewsById(productId: number): Observable<ProductReviewDto[]> {
    return this.http.get<ProductReviewDto[]>(`${this.baseUrl}/findAllProductReviewsByProductId/${productId}`);
  }

  addProductReview(productReview: ProductReviewDto): Observable<ProductReviewDto> {
    return this.http.post<ProductReviewDto>(`${this.baseUrl}/addProductReview`, productReview);
  }
}
