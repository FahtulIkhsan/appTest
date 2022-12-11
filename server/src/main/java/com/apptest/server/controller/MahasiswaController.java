package com.apptest.server.controller;

import com.apptest.server.entity.Mahasiswa;
import com.apptest.server.service.MahasiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mahasiswa")
@RequiredArgsConstructor
public class MahasiswaController {
    @Autowired
    private final MahasiswaService mahasiswaService;

    @PostMapping("/save")
    public ResponseEntity<Mahasiswa> saveMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/mahasiswa/save").toUriString());
        return ResponseEntity.created(uri).body(mahasiswaService.saveMahasiswa(mahasiswa));
    }

    @GetMapping
    public ResponseEntity<List<Mahasiswa>> getMahasiswa() {
        return ResponseEntity.ok().body(mahasiswaService.getMahasiswa());
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Mahasiswa> getMahasiswa(@PathVariable Long ID) {
        if(!mahasiswaService.isExist(ID)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(mahasiswaService.getMahasiswa(ID));
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<HttpStatus> deleteMahasiswa(@PathVariable Long ID) {
        if(!mahasiswaService.isExist(ID)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            mahasiswaService.deleteMahasiswa(ID);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
