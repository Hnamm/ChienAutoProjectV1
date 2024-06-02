import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Product } from '../common/product';
import { response } from 'express';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  private baseUrl = 'http://localhost:8080/api/products';
  private categoryUrl = 'http://localhost:8080/api/productCategory';
  constructor(private httpClient: HttpClient) { }

  getProductList(theCategoryId: number ): Observable<Product[]> {
    const searchUrl = `${this.categoryUrl}/${theCategoryId}/products`;

    if(theCategoryId === -1){
      return this.httpClient.get<GetResponseProduct>(this.baseUrl).pipe(
        map(response => response._embedded.products)
      );
    }

    return this.httpClient.get<Product[]>(searchUrl);
  }

  searchProducts(keyWord: string): Observable<Product[]>{
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${keyWord}`;

    return this.httpClient.get<GetResponseProduct>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  }

  // getProductCategories(): Observable<ProductCategory[]> {
  //   return this.httpClient.get<GetResponseProductCategory>(this.categoryUrl).pipe(
  //     map(response => response._embedded.productCategory)
  //   );
  // }
  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(this.categoryUrl);
  }
  // getProductCategories(): Observable<ProductCategory[]> {
  //   return this.httpClient.get<ProductCategory[]>(this.categoryUrl);
  // }
  getProduct(theProductId: number): Observable<Product> {
    const productUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
  }
}

interface GetResponseProduct{
  _embedded: {
    products: Product[];
  }
}

interface GetResponseProductCategory{
  _embedded: {
    productCategory: ProductCategory[];
  }
}



