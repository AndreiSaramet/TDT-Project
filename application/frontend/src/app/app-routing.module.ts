import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import {ProductPageComponent} from "./components/product-page/product-page.component";
import { UserPageComponent } from './components/user-page/user-page.component';
import { HomePageLayoutComponent } from './components/home-page-layout/home-page-layout.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import {EditProductComponent} from "./components/edit-product/edit-product.component";
import { ChangeUsernameComponent } from './components/change-username/change-username.component';
import { ChangeProfileComponent } from './components/change-profile/change-profile.component';
import { ReceivedFriendRequestsComponent } from './components/received-friend-requests/received-friend-requests.component';
const routes: Routes = [
  { path: 'main', component: MainPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'product/:id', component: ProductPageComponent },
  { path: 'home', component : HomePageLayoutComponent },
  { path: 'addProduct', component: AddProductComponent},
  { path: 'username/change', component: ChangeUsernameComponent},
  { path: 'profile/change', component: ChangeProfileComponent },
  { path: 'friend-requests/received', component: ReceivedFriendRequestsComponent },
  { path: '', component: HomePageLayoutComponent },
  { path: 'user', component: UserPageComponent, pathMatch: 'full' },
  { path: 'editProduct', component: EditProductComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
