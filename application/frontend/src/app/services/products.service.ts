import { Injectable } from '@angular/core';
import { Observable, Subject } from "rxjs";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../model/Product';
import { ThisReceiver } from '@angular/compiler';
import {ProductDTO} from "../model/productDto.model";
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private products: Subject<Product[]> = new Subject()

  private baseUrl: string = "http://localhost:8080"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  public getProductsObservable(): Observable<Product[]> {
    return this.products.asObservable()
  }

  public getAllProducts() {
    this.http.get<Product[]>(`${this.baseUrl}/findAllProducts`).subscribe(
      (value) => { this.products.next(value) }
    )
  }

  public getProductReviewsCount(productId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/countProductReviewsByProductId/${productId}`)
  }

  public getProductImage(productId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/findProductImageByProductId/${productId}`)
  }

  public getProductsByCategory(category: string) {
    this.http.get<Product[]>(`${this.baseUrl}/findProductsByCategory/${category}`).subscribe(
      (value) => { this.products.next(value) }
    )
  }

  public getProductsByPartialName(partialName: string) {
    this.http.get<Product[]>(`${this.baseUrl}/findProductsByPartialName/${partialName}`).subscribe(
      (value) => { this.products.next(value) }
    )
  }

  public getProductById(id: number): Observable<ProductDTO> {
    return this.http.get<ProductDTO>(`${this.baseUrl}/findProductById/${id}`);
  }

  public getAllProductsByUserId(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/findProductsByUserId/${id}`);
  }

  public addProduct(product: ProductDTO): Observable<ProductDTO> {
    return this.http.post<ProductDTO>(`${this.baseUrl}/addProduct`, product);
  }

  public addProductImage(formData: FormData, productId: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/saveProductImage/${productId}`, formData);
  }

  public loadProductImage(productId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/findProductImageByProductId/${productId}`);
  }

  public deleteProduct(productId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/deleteProduct/${productId}`);
  }

  public updateProduct(product: ProductDTO): Observable<ProductDTO> {
    return this.http.put<ProductDTO>(`${this.baseUrl}/updateProduct`, product);
  }

  public notifyFriendsNewProductEmail(adderId: number, productId: number): Observable<any> {
    return this.http.post<any>(`${environment.url}/notifyFriendsNewProductEmail/${adderId}/${productId}`, this.httpOptions);
  }
}
