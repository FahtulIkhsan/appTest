import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IMahasiswa } from 'app/models/mahasiswa';
import { RequestService } from 'app/services/request.service';
import { map, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  id: string = '';
  mahasiswa: IMahasiswa = {
    'id': null,
    'namaDepan': null,
    'namaBelakang': '',
    'tanggalLahir': null
  };
  usia: number;
  buttonType: string = 'POST';
  errorMessage: string;

  constructor(private activatedRouter: ActivatedRoute, private router: Router, private service: RequestService) { }
  
  ngOnInit() {
    let _activeChild = this.activatedRouter.children.length;
    if (_activeChild != 0) {
      this.activatedRouter.firstChild.paramMap.subscribe(params => {
        this.id = params.get('id');
      });
      this.service.getMahasiswaByID(this.id).subscribe(response => {
        this.mahasiswa = response;
        let timeDiff = Math.abs(Date.now() - Date.parse(this.mahasiswa.tanggalLahir.toString()));
        this.usia = Math.floor((timeDiff / (1000 * 3600 * 24)) / 365.25);
      });
    }
  }

  updateProfile(buttonType) {
    if (buttonType == 'DELETE') {
      this.service.deleteMahasiswa(this.id).subscribe({
        next: (v) => console.log(v),
        error: (e) => this.errorMessage = ('Aksi gagal dilaksanakan dengan keterangan : ' + e.message),
        complete: () => {
          alert('Aksi berhasil dilakukan');
          this.router.navigate(['/table']);
        }
      })
    } else {
      if (!this.mahasiswa.namaDepan) {
        this.errorMessage = 'Nama Depan belum terisi';
      } else if (!this.mahasiswa.tanggalLahir) {
        this.errorMessage = 'Tanggal Lahir belum terisi';
      } else {
        this.service.postMahasiswa(this.mahasiswa).subscribe({
          next: (v) => console.log(v),
          error: (e) => this.errorMessage = ('Aksi gagal dilaksanakan dengan keterangan : ' + e.message),
          complete: () => {
            alert('Aksi berhasil dilakukan');
            this.router.navigate(['/table']);
          }
        })
      }
    }
  }

}
