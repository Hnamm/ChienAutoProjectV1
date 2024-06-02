import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { FavoriteItem } from '../../common/favorite-item';
import { FavoriteService } from '../../services/favorite.service';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.css'
})
export class ProductDetailComponent implements OnInit{

  product!: Product;
  constructor( private productService: ProductService,
    private route : ActivatedRoute,
    private favoriteService: FavoriteService  
  ) {}; 

  ngOnInit(): void {
      this.route.paramMap.subscribe(() => {
        this.handleProductDetail();
      })
  }
  handleProductDetail() {
    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;
    
    this.productService.getProduct(theProductId).subscribe(
      data => {
        this.product = data;
      }
    )
  }
  addToFavorite(product: Product) {
    const favoriteItem = new FavoriteItem(product);

    this.favoriteService.addToFavoriteListService(favoriteItem);
  }

  

}
