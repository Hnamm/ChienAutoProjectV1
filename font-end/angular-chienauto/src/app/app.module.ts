import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProductListComponent } from './component/product-list/product-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './services/product.service';
import { HomeComponent } from './component/home/home.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './component/search/search.component';
import { ProductDetailComponent } from './component/product-detail/product-detail.component';
import { ProductCategoryMenuComponent } from './component/product-category-menu/product-category-menu.component';
import { FavoriteDetailComponent } from './component/favorite-detail/favorite-detail.component';
import { LoanEstimateProductComponent } from './component/loan-estimate-product/loan-estimate-product.component';
import { FormsModule } from '@angular/forms';
import { LoanMoneyTableComponent } from './component/loan-money-table/loan-money-table.component';
import { ActionsMenuComponent } from './component/actions-menu/actions-menu.component';

import { SigninComponent } from './component/signin/signin.component';
import { LoginComponent } from './component/login/login.component';




const routes: Routes = [
    {path: 'register', component: SigninComponent},
    {path: 'login', component: LoginComponent},
    {path: 'loanEstimate/:id/table', component: LoanMoneyTableComponent},
    {path: 'loanEstimate/:id', component: LoanEstimateProductComponent},
    {path: 'favorite-details', component: FavoriteDetailComponent},
    {path: 'products/:id', component: ProductDetailComponent},
    {path: 'search/:keyword', component: ProductListComponent},
    {path: 'productCategory/:id', component: ProductListComponent},
    {path: 'productCategory/:id/products', component: ProductListComponent},
    {path: 'productCategory', component: ProductListComponent},
    {path: 'products', component: ProductListComponent},
    {path: '', redirectTo: '/products', pathMatch: 'full'},
    {path: '**', redirectTo: '/products', pathMatch: 'full'},

]


@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    SearchComponent,
    ProductDetailComponent,
    ProductCategoryMenuComponent,
    FavoriteDetailComponent,
    LoanEstimateProductComponent,
    LoanMoneyTableComponent,
    ActionsMenuComponent,
    SigninComponent,
    LoginComponent,

    

  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    ProductService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
