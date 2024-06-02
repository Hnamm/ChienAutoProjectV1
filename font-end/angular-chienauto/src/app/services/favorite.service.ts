import { Injectable } from '@angular/core';
import { FavoriteItem } from '../common/favorite-item';
import { BehaviorSubject, Observable, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  private favoriteItems: FavoriteItem[] = [];
  private favoriteItemsSubject = new BehaviorSubject<FavoriteItem[]>(this.favoriteItems);


  constructor() { }

  getFavoriteItems(): Observable<FavoriteItem[]> {
    return this.favoriteItemsSubject.asObservable();
  }

  addToFavoriteListService(item: FavoriteItem) {
    const existingFavoriteItem = this.favoriteItems.find(favoriteItem => favoriteItem.id === item.id);

    if (!existingFavoriteItem) {
      this.favoriteItems.push(item);
      this.favoriteItemsSubject.next(this.favoriteItems);
    }
  }

  removeFavoriteListService(item: FavoriteItem){
    const index = this.favoriteItems.findIndex(favoriteItem => favoriteItem.id === item.id);
    if (index !== -1) {
        this.favoriteItems.splice(index, 1); 
        this.favoriteItemsSubject.next(this.favoriteItems);
        
    }
}

}
