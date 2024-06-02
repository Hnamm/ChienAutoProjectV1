import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { ActivatedRoute } from '@angular/router';
import { FavoriteItem } from '../../common/favorite-item';
import { FavoriteService } from '../../services/favorite.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',  
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{


  products: Product[] = [];
  currentCategoryId: number = -1;
  searchMode: boolean = false;

  constructor(private productService: ProductService,
                      private route: ActivatedRoute,
                      private favoriteService: FavoriteService
  ){}
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProduct();
    });
  }
  listProduct() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if(this.searchMode){
      this.handleSearchProduct();
    }else{
      this.handleListProduct();
    }
  }

  handleListProduct(){
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if(hasCategoryId){
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    }


    this.productService.getProductList(this.currentCategoryId).subscribe(
      data => {
        console.log(data);
        this.products = data;
      }
    );
  }

  handleSearchProduct(){
    const keyWord: string = this.route.snapshot.paramMap.get('keyword')!;

    this.productService.searchProducts(keyWord).subscribe(
      data => {
        this.products = data;
      }
    )
  }

  addToFavorite(product: Product) {
    const favoriteItem = new FavoriteItem(product);
    this.favoriteService.addToFavoriteListService(favoriteItem);
  }


}
