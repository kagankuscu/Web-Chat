import { Component } from '@angular/core';
import { AuthenticationService } from './authentication/services/authentication/authentication.service';
import { TokenService } from './authentication/services/authentication/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Web-Chat';
  constructor(private tokenService: TokenService) {
    tokenService.getToken().subscribe(s => console.log('app comp', s));
  }
}
