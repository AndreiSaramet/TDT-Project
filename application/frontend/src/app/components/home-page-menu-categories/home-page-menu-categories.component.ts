import { animate, query, stagger, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { MatChipListboxChange } from '@angular/material/chips';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-home-page-menu-categories',
  templateUrl: './home-page-menu-categories.component.html',
  animations: [
    trigger('fadeIn', [
      transition('* => *', [
        query('*', [
          style({ opacity: 0 }),
          stagger(10, [
            animate('0.35s', style({ opacity: 1 }))
          ])
        ], { optional: true })
      ])
    ])
  ],
  styleUrls: ['./home-page-menu-categories.component.css']
})
export class HomePageMenuCategoriesComponent implements OnInit {

  private NO_CATEGORY_SELECTED_VALUE = undefined

  constructor(private productsService: ProductsService) { }

  fadeIn = false

  ngOnInit(): void {
    this.fadeIn = true;
  }

  public hanldeCategoryChange(event: any) {
    let category = event.value
    if (category === this.NO_CATEGORY_SELECTED_VALUE) {
      this.productsService.getAllProducts() 
    } else {
      this.productsService.getProductsByCategory(category)
    }
  }
}
