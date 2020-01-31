import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";
import { faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { CategoryService } from "src/app/services/category.service";

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

  constructor(
    private userService: UserService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories;
    })
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

  addItem() {


  }
}
