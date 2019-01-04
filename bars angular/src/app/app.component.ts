import {Component, OnInit} from '@angular/core';
import { AppRestService } from './app-rest.service';
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent  {
  credsObservable: Observable<JsonUnwrap>;
  isAdmin: boolean;
  username: string;
  billingObservable: Observable<Record[]>;
  message: string;
  isFormLoaded: boolean;
  recordList: Record[];
  errorOutput: string = "hello";

  
  constructor(private restService: AppRestService) {  }
  
  ngOnChanges() {
    console.log("ERROR!!!" + this.errorOutput);
  }

  ngOnInit() {
    this.credsObservable=this.restService.getCredentialsAsToken();
    this.billingObservable=this.restService.getRecordsFromApi();


    this.credsObservable.subscribe(
      (data: JsonUnwrap) => {
        this.isAdmin= data.isAdmin;
        this.username = data.username;
      },
      (error: string) => {console.log(error)},
      ()=> {console.log('Username and role retrieved from BARS server.')}
    );

    this.billingObservable.subscribe(
      billingObservable => {
        this.recordList = billingObservable;
        this.errorOutput = this.recordList[0].lastName;
        
  
    console.log("OUTPUT: " + this.errorOutput);
      }
      );

  
    console.log("OUTPUT outside subscribe: " + this.errorOutput);
    }
  

    

    receiveFilePathFromFormComponent($event: any) {
      this.message = $event;
      console.log("APP COMPONENT RECEIVED : " + this.message)
      this.restService.getFilePathFromComponent(this.message);
      this.restService.postFilePathToApi();
    
    
    }
      // THIS ALWAYS RETURNS TRUE AND I DONT KNOW WHY!!!!!! 'x' == 'x'!!!!
    // if (this.username == 'x') {
    //   this.isLoggedIn = false;
    // } else {
    //   this.isLoggedIn = true;
    // }
    
    loadFormAgain() {
      this.isFormLoaded = true;
    }
  
  }

  
class JsonUnwrap {
   username: string;
   isAdmin: boolean ;
}

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

