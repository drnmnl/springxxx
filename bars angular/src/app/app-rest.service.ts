import { OnInit, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import {map, catchError, tap} from 'rxjs/operators';

class Record {
  billingId: number;
  billingCycle: number;
  startDate: string;
  endDate: string;
  accountName: string;
  lastName: string;
  firstName: string;
  amount: number;

  constructor (billingCycle: number,
    startDate: string,
    endDate: string
    ) 
    {
      this.billingCycle = billingCycle;
      this.startDate = startDate;
      this.endDate = endDate;

    }
  }

class Account {
  accountId: number;
  accountName: string;
  dateCreated: string;
  isActive: boolean;
  lastEdited: string;
  customerId: number;
}

class Billing {
  billingId: number;
  billingCycle: number;
  billingMonth: string;
  amount: number;
  startDate: string;
  endDate: string;
  lastEdited: string;
  accountId: number;
}

class Customer {
  customerId: number;
  firstName: string;
  lastName: string;
  address: string;
  status: string;
  date_created: string;
  last_edited: string;
}

class JsonUnwrap {
 username: string;
 isAdmin: boolean ;
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    responseType: 'blob',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AppRestService implements OnInit {
  billingObservable: Observable<Record[]>;
  filePath: string;
 
  constructor(private http: HttpClient) { }
  
  ngOnInit() {
    this.getRecordsFromApi();
    this.getCredentialsAsToken();
  }
  
  getFilePathFromComponent(filePath: string) {
    this.filePath = filePath;
    console.log("FILEPATH IN REST SERVICE TS " + this.filePath);
  }


  getCredentialsAsToken() {
    return this.http.get<JsonUnwrap>("http://localhost:9999/bars-server-service/loginstatus")
  }
  
  // POST
  postFilePathToApi() {
    console.log("POSTING FILEPATH TO SERVER: " + this.filePath);
    this.http.post<string>("http://localhost:9999/bars-server-service/sendfile", this.filePath).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("ERROR OCCURRED IN POST" + err);
      }
    );
  }
  
  // GET RECORDS
  getRecordsFromApi() {
    return this.http.get<Record[]>("http://localhost:9999/bars-server-service/upload");
  }
 
  postTestToApi() {
    console.log("POST TEST");
    this.http.post("http://localhost:9999/bars-server-service/test", "DAB WITH IT").subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log("ERROR OCCURRED IN POST" + err);
      }
    );
  }
  
  
}

// billingObservable: Observable<Billing[]>
// constructor(private http: HttpClient) { }
// ngOnInit() {
//   this.billingObservable = http.get<Billing[]>("http://localhost:8010/upload");
// }

// const req = this.http.post('http://jsonplaceholder.typicode.com/posts', {
//       title: 'foo',
//       body: 'bar',
//       userId: 1
//     })
//       .subscribe(
//         res => {
//           console.log(res);
//         },
//         err => {
//           console.log("Error occured");
//         }
//       