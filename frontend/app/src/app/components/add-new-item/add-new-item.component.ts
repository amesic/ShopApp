import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/services/user.service";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { faPlusCircle } from "@fortawesome/free-solid-svg-icons";
import { CategoryService } from "src/app/services/category.service";

@Component({
  selector: "app-add-new-item",
  templateUrl: "./add-new-item.component.html",
  styleUrls: ["./add-new-item.component.css"]
})
export class AddNewItemComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  item_image =
    "https://res.cloudinary.com/auctionabh/image/upload/v1580406474/ShopAppLogoPicture/600x600_vwq9cv.png";
  message_photo;
  categories;

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
      console.log(categories);
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
}
