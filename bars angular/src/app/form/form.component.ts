import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AppRestService } from '../app-rest.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  credsObservable: Observable<JsonUnwrap>;
  isLoggedIn: boolean;
  isAdmin: boolean;
  username: string;
  filePath: string;

  constructor(private restService: AppRestService) {
   }

  ngOnInit() {
    // this.credsObservable = this.restService.getCredentialsAsToken();

    // this.credsObservable.subscribe(
    //   (data: any) => {
    //     this.isAdmin= data.isAdmin;
    //     this.username = data.username;
    //   },
    //   (error: string) => {console.log(error)},
    //   ()=> {console.log('Username and role retrieved.')}
    // )
  }
  
  @Output() sendFilePathToAppComponentEvent = new EventEmitter<string>();   // event emitter will forward object to another component
  
  fileUploaded(e) {
    this.sendFilePathToAppComponentEvent.emit(e.target.value);  // emits file to AppComponent (PARENT)
    this.restService.postTestToApi(); // sends test POST request to BARS API when a file is uploaded (testing purposes only!)
  }
}

class JsonUnwrap {
  username: string;
  isAdmin: boolean ;
}