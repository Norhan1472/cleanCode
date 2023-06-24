import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {
  urlAPI = environment.UtilsURL;
  constructor(private httpClient:HttpClient) { }
  insertUtils(data:any):Observable<any>{
    return this.httpClient.post<any>(`${this.urlAPI}Utils/createUtils`,data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }

  getTopicsById(id:number):Observable<any>
  {
   return this.httpClient.get<any>
   (
    // link we get data from the server and give it special id
    `${this.urlAPI}details/${id}`,
    //
    {headers:new HttpHeaders().set('Content-Type','application/json')}
   )
  }

  genrateReport(id:number)
  {
    return this.httpClient.get<any>
    (
      `${this.urlAPI}utils/generateFunctionReport/${id}`,
      {headers:new HttpHeaders().set('Content-Type','application/json')}
    )
  }
updateUtils(data:any,id:any):Observable<any>{
  return this.httpClient.put<any>(`${this.urlAPI}Utils/updateUtils/${id}`,data,{
    headers:new HttpHeaders().set('Content-Type','application/json')
  })
}




}






  // getAllfunctionss():Observable<any>
  // {
  //   return this.httpClient.get<any>
  //   (
  //     // 1-link that get data form the server
  //       `${this.urlAPI}utils/getAllUtils`,
  //     // 2-type of header request
  //       { headers:new HttpHeaders().set("Content-Type","appliacation/json")}

  //   )

  // }

  // editFunction(data:[]):Observable<any>
  // {
  //   return this.httpClient.put
  //   (
  //     //1-link
  //       `${this.urlAPI}utils/updateUtils`,
  //     //2-UpdatedData
  //       data,
  //     //3-type of HeaderRequest
  //       {headers:new HttpHeaders().set("Content-Type","application/json")}

  //   )
  // }



