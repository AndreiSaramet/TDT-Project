import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment.prod';
import {JwtHelperService} from "@auth0/angular-jwt";
import { UserDTO } from '../model/userDto.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(
    private client: HttpClient,
    private jwtHelper: JwtHelperService,
  ) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  public findUserById(userId: number): Observable<any> {
    return this.client.get<any>(`${environment.url}/findUserById/${userId}`);
  }

  getCurrentUserId(): string {
    const token = localStorage.getItem("token") as string;
    return this.jwtHelper.decodeToken(token)['id'];
  }

  public areUsersFriends(currentUserId: number, otherUserId: number): Observable<any> {
    return this.client.get<any>(`${environment.url}/isFriend/${currentUserId}/${otherUserId}`);
  }

  public findFriendRequestBySenderAndReceiver(senderId: number, receiverId: number): Observable<any> {
    return this.client.get<any>(`${environment.url}/findFriendRequestBySenderAndReceiver/${senderId}/${receiverId}`);
  }

  public findAllFriendRequestsByReceiver(receiverId: number): Observable<any> {
    return this.client.get<any>(`${environment.url}/findAllFriendRequestsByReceiver/${receiverId}`);
  }

  public sendFriendRequest(senderId: number, receiverId: number): Observable<any> {
    return this.client.post<any>(`${environment.url}/sendFriendRequest/${senderId}/${receiverId}`, this.httpOptions);
  }

  public acceptFriendRequest(senderId: number, receiverId: number): Observable<any> {
    return this.client.post<any>(`${environment.url}/acceptFriendRequest/${senderId}/${receiverId}`, this.httpOptions);
  }

  public removeFriendRequest(senderId: number, receiverId: number): Observable<any> {
    return this.client.delete<any>(`${environment.url}/removeFriendRequest/${senderId}/${receiverId}`);
  }

  public removeFriend(userId: number, friendId: number): Observable<any> {
    return this.client.delete<any>(`${environment.url}/removeFriend/${userId}/${friendId}`);
  }

  public getUserFriends(userId: number): Observable<any> {
    return this.client.get<any[]>(`${environment.url}/findAllFriends/${userId}`);
  }

  public updateUser(newUser: UserDTO): Observable<any> {
    return this.client.put<any>(`${environment.url}/updateUser`, newUser);
  }
}
