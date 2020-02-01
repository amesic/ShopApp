import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";
import { faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { CategoryService } from "src/app/services/category.service";
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: "app-add-new-item",
  templateUrl: "./add-new-item.component.html",
  styleUrls: ["./add-new-item.component.css"]
})
export class AddNewItemComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  faChevronDown = faChevronDown;
  item_image =
    "https://res.cloudinary.com/auctionabh/image/upload/v1580406474/ShopAppLogoPicture/600x600_vwq9cv.png";
  message_photo;
  categories;
  subcategories;
  brands;
  show_categories = false;
  show_subcategories = false;
  show_brands = false;
  picked_category = "Item Category";
  picked_subcategory = "Item Subcategory";
  picked_brand = "Item Brand";

  add = new FormGroup({
    name: new FormControl("", Validators.required),
    price: new FormControl("", Validators.required),
    description: new FormControl("", Validators.required)
  });

  fileData: File;
  previewUrl: any;

  errorName;
  errorPrice;
  errorDescription;
  errorImage;
  errorCategory;
  errorSubcategory;
  errorBrand;

  message;
  type;

  loading = false;

  constructor(
    public userService: UserService,
    private categoryService: CategoryService,
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories;
    });

  }

  onFileSelect(fileInput: any) {
    this.fileData = <File>fileInput.target.files[0];
    this.preview();
  }

  preview() {
    var mimeType = this.fileData.type;
    if (mimeType.match(/image\/*/) == null) {
      this.message_photo = "Image is invalid!";
    } else {
      this.message_photo = "";
      var reader = new FileReader();
      reader.readAsDataURL(this.fileData);
      reader.onload = _event => {
        this.previewUrl = reader.result;
        this.item_image = this.previewUrl;
      };
    }
  }

  showHideCategories() {
    this.show_categories = !this.show_categories;
    this.show_subcategories = false;
    this.show_brands = false;
  }

  showHideSubCategories() {
    this.show_subcategories = !this.show_subcategories;
    this.show_categories = false;
    this.show_brands = false;
  }

  showHideBrands() {
    this.show_brands = !this.show_brands;
    this.show_subcategories = false;
    this.show_categories = false;
  }

  getSubcategoriesAndBrands(category) {
    this.picked_category = category.name;
    this.show_categories = false;
    this.categoryService.getSubcategoriesOfCategory(category.name).subscribe(subcategories => {
      this.subcategories = subcategories;
      this.picked_subcategory = "Item Subcategory";
      this.picked_brand = "Item Brand";
    });
    this.categoryService.getBrandsOfCategory(category.name).subscribe(brands => {
      this.brands = brands;
    });
  }

  pickSubcategory(subcategory) {
    this.picked_subcategory = subcategory.name;
    this.show_subcategories = false;
  }

  pickBrand(brand) {
    this.picked_brand = brand.name;
    this.show_brands = false;
  }

  _keyUp(event: any) {
    const pattern = /^[0-9]*$/;
    if (!pattern.test(event.target.value)) {
      event.target.value = null;
    }
}

  get name() {
    return this.add.get("name");
  }
  get price() {
    return this.add.get("price");
  }
  get description() {
    return this.add.get("description");
  }

  addItem() {
    if (this.name.errors != null) {
      this.errorName = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorName = false;
      this.message = undefined;
    }
    if (this.price.errors != null) {
      this.errorPrice = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorPrice = false;
      this.message = undefined;
    }
    if (this.description.errors != null) {
      this.errorDescription = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorDescription = false;
      this.message = undefined;
    }
    if (this.picked_category == "Item Category") {
      this.errorCategory = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorCategory = false;
      this.message = undefined;
    }
    if (this.picked_subcategory == "Item Subcategory") {
      this.errorSubcategory = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorSubcategory = false;
      this.message = undefined;
    }
    if (this.picked_brand == "Item Brand") {
      this.errorBrand = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.errorBrand = false;
      this.message = undefined;
    }
    if (this.item_image == "https://res.cloudinary.com/auctionabh/image/upload/v1580406474/ShopAppLogoPicture/600x600_vwq9cv.png") {
      this.errorImage = true;
      this.message = "Enter all the necessary information below!";
      this.type = "error";
      window.scroll(0,0);
    } else {
      this.message = undefined;
      this.errorImage = false;
    }

    if (this.name.errors == null &&
      this.price.errors == null &&
      this.description.errors == null &&
      this.picked_category != "Item Category" &&
      this.picked_subcategory != "Item Subcategory" &&
      this.picked_brand != "Item Brand" &&
      this.item_image != "https://res.cloudinary.com/auctionabh/image/upload/v1580406474/ShopAppLogoPicture/600x600_vwq9cv.png") {
      let company;
      if (this.userService.getUserName() == "AdminCM") {
        company = "Cosmetic Market (CM)";
      } else if (this.userService.getUserName() == "AdminDM") {
        company = "Drogerie Markt (DM)";
      }
      this.loading = true;
      window.scroll(0,0);
      this.productService.saveNewItem(
          this.name.value,
          this.price.value,
          this.description.value,
          this.item_image,
          this.picked_category,
          this.picked_subcategory,
          this.picked_brand,
          company).subscribe(listOfProductsJSON => {
            this.loading = false;
            this.message = "Item successfully saved!";
            this.type = "success";
            this.add.reset();
            this.picked_brand = "Item Brand";
            this.picked_category = "Item Category";
            this.picked_subcategory = "Item Subcategory";
            this.brands = undefined;
            this.subcategories = undefined;
            this.item_image = "https://res.cloudinary.com/auctionabh/image/upload/v1580406474/ShopAppLogoPicture/600x600_vwq9cv.png";
          }, err => console.log(err.error));
      }

  }
}
