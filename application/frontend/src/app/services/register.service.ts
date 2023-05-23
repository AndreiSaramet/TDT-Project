import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { RegisterDTO } from '../model/registerDto.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private client: HttpClient) { }

  public registerUser(registerDto: RegisterDTO): Observable<any> {
    return this.client.post<any>(`${environment.url}/addUser`, registerDto);
  }
}
