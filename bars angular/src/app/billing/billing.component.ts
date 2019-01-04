import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { AppRestService } from '../app-rest.service';

class Record {
  billingId: number;
  billingCycle: number;
  startDate: string;
  endDate: string;
  accountName: string;
  lastName: string;
  firstName: string;
  amount: number;
}


@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.css']
})
export class BillingComponent implements OnInit {
  billingObservable: Observable<Record[]>;
  isExceptionThrown: boolean;
  exceptionThrown: string;

  constructor(private restService: AppRestService) {}
  ngOnInit() {
    this.billingObservable = this.restService.getRecordsFromApi();
    }

  }

  
  // showReturnedRecords() {}




  // billingObservable: Observable<Billing[]>
  // constructor(private http: HttpClient) { }

  // ngOnInit() {
  //   this.billingObservable = this.http.get<Billing[]>("http://localhost:8010/upload");
  // }

  // ngOnInit() {}
  // 
