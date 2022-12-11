import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IMahasiswa } from 'app/models/mahasiswa';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private httpClient: HttpClient) { }

  getMahasiswa(){
    return this.httpClient.get<IMahasiswa[]>('/api');
  }

  getMahasiswaByID(id : string){
    return this.httpClient.get<IMahasiswa>('/api'.concat('/',id.toString()));
  }

  postMahasiswa(mahasiswa : IMahasiswa){
    return this.httpClient.post<any>('/api/save', mahasiswa, {observe:'response'});
  }

  deleteMahasiswa(id: string){
    return this.httpClient.delete<any>('/api'.concat('/',id.toString()), {observe:'response'});
  }
}
