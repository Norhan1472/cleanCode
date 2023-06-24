import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  urlApi = environment.TopicURL;

  constructor(private httpClient:HttpClient) { }

  getAllTopics():Observable<any>{
    return this.httpClient.get<any>(`${this.urlApi}getAllTopic`,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
}
