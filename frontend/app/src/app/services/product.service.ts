import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product } from "../models/Product";
import { PaginationInfo } from "../models/PaginationInfo";

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json"
  })
};

@Injectable({
  providedIn: "root"
})
export class ProductService {
  urlNewArrivals = "/api/product/newArrivals";
  urlSaveNewItem = "/auth/product/save";

  constructor(private http: HttpClient) {}

  getNewArrivals(page, size): Observable<PaginationInfo<Product>> {
    return this.http.get<PaginationInfo<Product>>(
      this.urlNewArrivals + "?page=" + page + "&size=" + size
    );
  }

  saveNewItem(
    name,
    price,
    description,
    image_url,
    category,
    subcategory,
    brand,
    company
  ): Observable<Product[]> {
    return this.http.post<Product[]>(
      this.urlSaveNewItem,
      {
        name: name,
        price: price,
        description: description,
        brand: {
          name: brand
        },
        image: image_url,
        category: {
          name: category
        },
        subcategory: {
          name: subcategory
        },
        company: {
          name: company
        }
      },
      httpOptions
    );
  }
}
