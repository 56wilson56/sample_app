import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  template: `
    <input [(ngModel)]="username" placeholder="Enter your username">
    <input [(ngModel)]="password" placeholder="Enter your password" type="password">
    <button (click)="login()">Login</button>
  `
})
export class AppComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient) {}

  login() {
    // Security Bug: Sends sensitive data (username/password) in plain text without encryption.
    this.http.post('http://localhost:8080/login', { username: this.username, password: this.password })
      .subscribe(response => console.log('Logged in'));
  }
}
