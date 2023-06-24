import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IssueInfoService {

  urlApi = environment.IssueURL;
  constructor(private httpClient:HttpClient) { }

  getAll():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAll`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    });
  }
  addIssueInfo(issueInfo:any):Observable<any>{
    return this.httpClient.post<any>(`${this.urlApi}addIssueInfo`,issueInfo,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  getIssueById(id:number)
  {
    return this.httpClient.get<any>
    (
      //link
      `${this.urlApi}getIssueInfoById/${id}`,
      //h req type
      {headers:new HttpHeaders().set("Content-Type","application/json")}

    )
  }

  deleteIssueId(id:number):Observable<any>
  {
    return this.httpClient.delete<any>
    (
      //link
      `${this.urlApi}deleteIssueInfo/${id}`,
      //type
      {headers:new HttpHeaders().set("Content-Type","application/json")}
    )
  }
}
