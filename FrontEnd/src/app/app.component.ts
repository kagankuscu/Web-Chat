import { Component } from '@angular/core';
import { AuthenticationService } from './authentication/services/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Web-Chat';
  constructor(private authService: AuthenticationService) {
    // authService.tokenObservable.subscribe(s => console.log('app comp', s));
  }
}
