import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/Category';
import { HttpClient } from "@angular/common/http";
import { Brand } from '../models/Brand';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  urlAllCategories = "/api/category/all";
  urlSubcategoriesOfCategory = "/api/category/subcategories";
  urlBrandsOfCategory = "/api/category/brands";

  constructor(private http: HttpClient) { }

  getAllCategories() : Observable<Category[]> {
    return this.http.get<Category[]>(this.urlAllCategories);
  }

  getSubcategoriesOfCategory(name: string) : Observable<Category[]> {
    return this.http.get<Category[]>(this.urlSubcategoriesOfCategory + "?name=" + name);
  }

  getBrandsOfCategory(name: string) : Observable<Brand[]> {
    return this.http.get<Brand[]>(this.urlBrandsOfCategory + "?name=" + name);
  }
}
