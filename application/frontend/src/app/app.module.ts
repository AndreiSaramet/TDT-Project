import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageLayoutComponent } from './components/home-page-layout/home-page-layout.component';
import { HomePageMenuComponent } from './components/home-page-menu/home-page-menu.component';
import { HomePageWorkspaceComponent } from './components/home-page-workspace/home-page-workspace.component';
import { HomePageMenuUserComponent } from './components/home-page-menu-user/home-page-menu-user.component';
import { HomePageMenuCategoriesComponent } from './components/home-page-menu-categories/home-page-menu-categories.component';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatListModule } from '@angular/material/list';
import { ProductComponentComponent } from './components/product-component/product-component.component';
import { MatMenuModule } from '@angular/material/menu';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { OptionListComponent } from './components/option-list/option-list.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { ProductPageComponent } from './components/product-page/product-page.component';
import { MatCardModule } from "@angular/material/card";
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { MatTooltipModule } from "@angular/material/tooltip";
import { UserPageComponent } from './components/user-page/user-page.component';
import { ProductComponent } from './components/product/product.component';
import { JwtHelperService, JwtModule, JWT_OPTIONS } from '@auth0/angular-jwt';
import { CommonFooterComponent } from './common-footer/common-footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ProductReviewComponent } from './components/product-reviews/product-review/product-review.component';
import { ProductReviewsComponent } from './components/product-reviews/product-reviews.component';
import { AddReviewComponent } from './components/product-reviews/add-review/add-review.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { EditProductComponent } from './components/edit-product/edit-product.component';
import { MatDividerModule } from '@angular/material/divider'; 
import { MatSidenavModule } from '@angular/material/sidenav';
import { ChangeUsernameComponent } from './components/change-username/change-username.component';
import { ChangeProfileComponent } from './components/change-profile/change-profile.component';
import { ReceivedFriendRequestsComponent } from './components/received-friend-requests/received-friend-requests.component'; 

@NgModule({
  declarations: [
    AppComponent,
    HomePageLayoutComponent,
    HomePageMenuComponent,
    HomePageWorkspaceComponent,
    HomePageMenuUserComponent,
    HomePageMenuCategoriesComponent,
    ProductComponentComponent,
    LoginPageComponent,
    RegisterPageComponent,
    MainPageComponent,
    OptionListComponent,
    ProductPageComponent,
    StarRatingComponent,
    UserPageComponent,
    ProductComponent,
    CommonFooterComponent,
    ProductReviewComponent,
    ProductReviewsComponent,
    AddReviewComponent,
    AddProductComponent,
    EditProductComponent,
    ChangeUsernameComponent,
    ChangeProfileComponent,
    ReceivedFriendRequestsComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    MatInputModule,
    MatIconModule,
    MatChipsModule,
    MatCardModule,
    MatPaginatorModule,
    MatListModule,
    MatMenuModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTooltipModule,
    JwtModule,
    MatDividerModule,
    MatSidenavModule
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
