import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import { BillingComponent } from './billing/billing.component';
import { FormComponent } from './form/form.component';
import {Routes,RouterModule} from '@angular/router';
import { AppRestService } from './app-rest.service';

@NgModule({
  declarations: [
    AppComponent,
    BillingComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [AppRestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
