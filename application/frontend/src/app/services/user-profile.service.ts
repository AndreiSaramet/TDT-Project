import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { userProfileDto } from '../model/userProfileDto.model';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  constructor(private http: HttpClient) { }

  addUserProfile(userId:number, form:FormData): Observable<any>{
    return this.http.post<any>(`${environment.url}/addUserProfile/${userId}`, form);
  }

  findUserProfileByUserId(userId: number): Observable<any>{
    return this.http.get<userProfileDto>(`${environment.url}/findUserProfileByUserId/${userId}`);
  }

  loadImages(userId: number): Observable<any>{
    return this.http.get<userProfileDto>(`${environment.url}/findAllFriends/${userId}`);
  }
}
