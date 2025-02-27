import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IUsers} from '../interfaces/i-users';

@Injectable({
  providedIn: 'root'
})

export class CargaUserService {

  private URL_SERVER_USERS = 'http://localhost:8000/api-rest/users';

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<IUsers[]> {
    return this.http.get<IUsers[]>(this.URL_SERVER_USERS);
  }

  getUser(idUser: number): Observable<IUsers> {
    return this.http.get<IUsers>(this.URL_SERVER_USERS + '/' + idUser);
  }

  modificarUser(user: IUsers): Observable<IUsers> {
    return this.http.put<IUsers>(user.url, user);
  }

  nuevoUser(user: IUsers): Observable<IUsers> {
    return this.http.post<IUsers>(this.URL_SERVER_USERS, user);
  }
}
