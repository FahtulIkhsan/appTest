import { Component, OnInit } from '@angular/core';
import { IMahasiswa } from 'app/models/mahasiswa';
import { RequestService } from 'app/services/request.service';

declare interface TableData {
    headerRow: string[];
    dataRows: string[][];
}

@Component({
    selector: 'app-tables',
    templateUrl: './tables.component.html',
    styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {
    public tableData1: TableData;
    public listMahasiswa: IMahasiswa[];

    constructor(private service: RequestService) { }

    ngOnInit() {
        this.tableData1 = {
            headerRow: ['Nomor Induk Mahasiswa', 'Nama Lengkap', 'Usia', 'Detail'],
            dataRows: []
        };
        this.service.getMahasiswa()
            .subscribe(response => {
                this.listMahasiswa = response;
                for (let mahasiswa of this.listMahasiswa) {
                    mahasiswa.namaBelakang = mahasiswa.namaBelakang || '';
                }
                try {
                    for (let mahasiswa of this.listMahasiswa) {
                        let timeDiff = Math.abs(Date.now() - Date.parse(mahasiswa.tanggalLahir.toString()));
                        let age = Math.floor((timeDiff / (1000 * 3600 * 24)) / 365.25);
                        let fullName = mahasiswa.namaDepan.toString().concat(' ', mahasiswa.namaBelakang.toString());
                        let arr: string[] = [mahasiswa.id.toString(), fullName, age.toString()];
                        this.tableData1.dataRows.push(arr);
                    }
                } catch (e) {
                    console.log('invalid json')
                }
            })
    }

}
