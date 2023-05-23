import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/Product';
import { PageEvent } from '@angular/material/paginator';
import { animate, query, stagger, style, transition, trigger } from '@angular/animations';
import { ProductsService } from 'src/app/services/products.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-page-workspace',
  templateUrl: './home-page-workspace.component.html',
  animations: [
    trigger('listAnimation', [
      transition('* => *', [
        query(':enter', [
          style({ opacity: 0 }),
          stagger(100, [
            animate('0.5s', style({ opacity: 1 }))
          ])
        ], { optional: true })
      ])
    ])
  ],
  styleUrls: ['./home-page-workspace.component.css']
})
export class HomePageWorkspaceComponent implements OnInit {

  public products: Product[] = []
  public productsToDisplay: Product[] = []

  public page: number = 0;
  public perPage: number = 5;

  
  constructor(private service: ProductsService,
              private activatedRoute: ActivatedRoute) { 
    this.service.getProductsObservable().subscribe(
      (value) => { 
        this.products = value 
        this.setProductsToDisplay()
      }
    )
    this.service.getAllProducts()
  }


  ngOnInit(): void {
    const text = this.activatedRoute.snapshot.queryParams["text"];
    if (text) {
      this.service.getProductsByPartialName(text);
    }
  }

  public onChangePage(pe: PageEvent) {
    this.page = pe.pageIndex;
    this.perPage = pe.pageSize;
    this.setProductsToDisplay();
  }

  private setProductsToDisplay() {
    this.productsToDisplay = this.products.slice(this.page * this.perPage, (this.page + 1) * this.perPage);
  }
}
