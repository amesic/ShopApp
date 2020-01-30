import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product } from "../models/Product";
import { PaginationInfo } from '../models/PaginationInfo';

@Injectable({
  providedIn: "root"
})
export class ProductService {
  urlNewArrivals = "/api/product/newArrivals";

  constructor(private http: HttpClient) {}

  getNewArrivals(page, size): Observable<PaginationInfo<Product>> {
    return this.http.get<PaginationInfo<Product>>(
      this.urlNewArrivals + "?page=" + page + "&size=" + size
    );
  }
}
