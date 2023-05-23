import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { Injectable } from '@angular/core';
import { LoginDTO } from '../model/loginDto.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private client: HttpClient) { }

  public loginUser(loginDto: LoginDTO) {
    return this.client.post(`${environment.url}/login`, loginDto);
  }
}
