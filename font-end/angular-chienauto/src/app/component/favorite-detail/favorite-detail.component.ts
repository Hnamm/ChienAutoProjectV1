import { Component, OnInit } from '@angular/core';
import { FavoriteItem } from '../../common/favorite-item';
import { FavoriteService } from '../../services/favorite.service';
import { Product } from '../../common/product';

@Component({
  selector: 'app-favorite-detail',
  templateUrl: './favorite-detail.component.html',
  styleUrl: './favorite-detail.component.css'
})
export class FavoriteDetailComponent implements OnInit{

  favoriteItems: FavoriteItem[] = [];
  

  constructor(private favoriteService: FavoriteService){}
  ngOnInit(): void {
   this.handleListFavorite();
  }

  handleListFavorite(){
    this.favoriteService.getFavoriteItems().subscribe(
      data=>{
        this.favoriteItems = data
      }
    )
  }
  removeFavorite(item: FavoriteItem) {
    this.favoriteService.removeFavoriteListService(item);
  }
;


}
