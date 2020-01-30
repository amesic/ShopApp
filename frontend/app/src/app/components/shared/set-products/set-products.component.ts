import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { PaginationInfo } from 'src/app/models/PaginationInfo';
import { Product } from 'src/app/models/Product';
import { ProductService } from 'src/app/services/product.service';
import { checkIfThereIsNoItemsLeft } from '../../../utils/checkIfThereIsNoItemsLeft';

@Component({
  selector: 'app-set-products',
  templateUrl: './set-products.component.html',
  styleUrls: ['./set-products.component.css']
})
export class SetProductsComponent implements OnInit, OnChanges {
  @Input() paginationInfo: PaginationInfo<Product>;
  @Input() title;
  @Input() page;
  @Input() size;

  message_products_empty;
  products;
  hide_button = false;

  constructor(private productService: ProductService) { }

  ngOnInit() {
  }
  ngOnChanges() {
    if (this.paginationInfo != undefined) {
      console.log(this.paginationInfo);
      if (this.paginationInfo.items.length != 0) {
        this.message_products_empty = "";
        this.products = this.paginationInfo.items;
        if (checkIfThereIsNoItemsLeft(this.page, this.size, this.paginationInfo.totalNumberOfItems)) {
          this.hide_button = true;
        }
      } else {
        this.message_products_empty = "There are no New Arrivals";
        this.hide_button = true;
        this.products = [];
      }
    }
  }

  getNextPage() {
    this.productService.getNewArrivals(this.page, this.size).subscribe(paginationInfo => {
        if (paginationInfo.items.length != 0) {
          this.message_products_empty = "";
          this.products = this.products.concat(paginationInfo.items);
          this.page++;
          if (checkIfThereIsNoItemsLeft(this.page, this.size, paginationInfo.totalNumberOfItems)) {
            this.hide_button = true;
          }
        } else {
          this.products = null;
          this.hide_button = true;
          this.message_products_empty = "There are no New Arrivals";
        }
      });
  }

}
