import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams,} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService  {
  private http = inject(HttpClient);

  // This is the proxy path
  private baseUrl = 'http://localhost:3001/api';

  constructor() {}

  postData(endpoint: string, payload: any): Observable<any> {
    const url = `${this.baseUrl}/${endpoint}`;
    return this.http.post<any>(url, payload, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      withCredentials: true
    }).pipe(
      catchError(error => {
        console.error('POST error:', error);
        return throwError(() => error);
      })
    );
  }

  postDataWithParams(endpoint: string, payload: any, queryParams: Record<string, any>): Observable<any> {
    const url = `${this.baseUrl}/${endpoint}`;
    const params = new HttpParams({ fromObject: queryParams });

    return this.http.post<any>(url, payload, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      params,
      withCredentials: true
    }).pipe(
      catchError(error => {
        console.error('POST with params error:', error);
        return throwError(() => error);
      })
    );
  }


  getData(endpoint: string): Observable<any> {
    const url = `${this.baseUrl}/${endpoint}`;
    return this.http.get(url, {
      withCredentials: true
    });
  }

  getDataWithParams(endpoint: string, queryParams: Record<string, any>): Observable<any> {
    const url = `${this.baseUrl}/${endpoint}`;
    const params = new HttpParams({ fromObject: queryParams });

    return this.http.get(url, {
      params,
      withCredentials: true
    });
  }

  getNotebooks() {
   return this.getData('service/getAllNotebooks/4');
  }

  /**intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const cloned = req.clone({
      setHeaders: {
        'Content-Type': 'application/json'
      }
    });
    return next.handle(cloned).pipe(
      catchError(error => {
        console.error('HTTP Interceptor caught an error:', error);
        return throwError(() => error);
      })
    );
  }*/
}
