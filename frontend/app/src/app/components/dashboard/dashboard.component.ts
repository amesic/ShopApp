import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  page = 0;
  size = 4;
  newArrivalsInfo;

  constructor(private productService: ProductService) { }


  ngOnInit() {
    this.productService.getNewArrivals(this.page, this.size).subscribe(paginationInfoProducts => {
      this.newArrivalsInfo = paginationInfoProducts;
      this.page++;
    })
  }

}
