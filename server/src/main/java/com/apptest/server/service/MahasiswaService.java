package com.apptest.server.service;

import com.apptest.server.entity.Mahasiswa;

import java.util.List;

public interface MahasiswaService {
    Mahasiswa saveMahasiswa(Mahasiswa mahasiswa);
    Mahasiswa getMahasiswa(Long ID);
    void deleteMahasiswa(Long ID);
    List<Mahasiswa> getMahasiswa();
    boolean isExist(Long ID);
}
