import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-home-page-menu-user',
  templateUrl: './home-page-menu-user.component.html',
  styleUrls: ['./home-page-menu-user.component.css']
})
export class HomePageMenuUserComponent implements OnInit {

  private KEYDOWN_EVENT_ENTER: string = 'Enter'
  public DEFAULT_SEARCH_BAR_VALUE: string = ''
  public searchValue: string = this.DEFAULT_SEARCH_BAR_VALUE

  isUserLoggedIn: any = null;

  constructor(
    private jwtHelper: JwtHelperService,
    private route: ActivatedRoute,
    private router: Router,
    private productsService: ProductsService  
  ) { }

  ngOnInit(): void {
    this.isUserLoggedIn = this.isLoggedIn();
  }


  public search(event: KeyboardEvent) {
    if(event.key === this.KEYDOWN_EVENT_ENTER) {
      let processedString = this.searchValue.trim().toLowerCase()
      this.productsService.getProductsByPartialName(processedString)
      this.searchValue = this.DEFAULT_SEARCH_BAR_VALUE
      this.router.navigate(["/home"], {queryParams: {text: processedString}});
    }
  }


  public tryRouteProducts() {
      if (!this.isLoggedIn()) {
        this.router.navigate(["/login"]);
      } else {
        this.router.navigate(["/home"]);
      }
  }

  public tryRouteUserPage() {
    if (!this.isLoggedIn()) {
      this.router.navigate(["/login"]);
    } else {
      this.router.navigate(["/main"]);
    }
  }

  public routeLogout() {
      localStorage.removeItem("token");
      this.router.navigate(["/login"]);
  }

  public routeLogin() {
    this.router.navigate(["/login"]);
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem("token") !== null) {
      const token = localStorage.getItem("token") as string;
      return !this.jwtHelper.isTokenExpired(token);
    } else {
      return false;
    }
  }
}
