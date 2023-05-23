import {Component} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {UserProfileService} from "../../services/user-profile.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {JwtHelperService} from "@auth0/angular-jwt";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductDTO} from "../../model/productDto.model";
import {ProductsService} from "../../services/products.service";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  nameFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  descriptionFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  priceFormControl = new FormControl(0, [Validators.required, Validators.min(1)]);
  categoryFormControl = new FormControl('', [Validators.required, Validators.minLength(2)]);
  selectedFile: any = null;
  fileUpload: any = null;
  picture: any;
  userId: any;

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] ?? null;
  }

  changedPicture(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.selectedFile = event.target.files[0];
    }
    this.readURL(event);
  }

  readURL(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0];

      const reader = new FileReader();
      reader.onload = e => this.picture = reader.result;

      reader.readAsDataURL(file);
    }
  }

  constructor(private productService: ProductsService,
              private userProfileService: UserProfileService,
              private snackBar: MatSnackBar,
              private jwtHelper: JwtHelperService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    if (localStorage.getItem("token") !== null) {
      const token = localStorage.getItem("token") as string;
      if (!this.jwtHelper.isTokenExpired(token)) {
        this.userId = this.jwtHelper.decodeToken(token).id;
        console.log(this.userId);
      }

    }
  }

  addProduct(): void {
    if (this.priceFormControl.value! <= 0) {
      return;
    }
    const productDTO: ProductDTO = {
      name: this.nameFormControl.value!,
      description: this.descriptionFormControl.value!,
      price: this.priceFormControl.value!,
      category: this.categoryFormControl.value!,
      userId: this.userId
    }
    let formData = new FormData();
    formData.append('image', this.selectedFile);
    this.productService.addProduct(productDTO)
      .subscribe({
        error: () => this.snackBar.open("Failed to add product", "x"),
        next: (product: ProductDTO) => {
          this.productService.notifyFriendsNewProductEmail(this.userId, product.id!).subscribe();
          this.productService.addProductImage(formData, product.id).subscribe({
            next: () => {
              this.snackBar.open("Product added successfully!", "x");
              this.router.navigate(["/main"]);
            }
          });

        }
      });
  }
}
