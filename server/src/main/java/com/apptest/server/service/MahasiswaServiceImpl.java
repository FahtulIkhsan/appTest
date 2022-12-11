package com.apptest.server.service;

import com.apptest.server.entity.Mahasiswa;
import com.apptest.server.repo.MahasiswaRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MahasiswaServiceImpl implements MahasiswaService{
    @Autowired
    private final MahasiswaRepo mahasiswaRepo;
    @Override
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) {
        log.info("Saving new mahasiswa {} to the database", mahasiswa.getNamaDepan());
        return mahasiswaRepo.save(mahasiswa);
    }

    @Override
    public Mahasiswa getMahasiswa(Long ID) {
        log.info("Fetching mahasiswa {}", ID);
        return mahasiswaRepo.findByID(ID);
    }

    @Override
    public void deleteMahasiswa(Long ID) {
        log.info("Deleting mahasiswa {}", ID);
        mahasiswaRepo.deleteById(ID);
    }

    @Override
    public List<Mahasiswa> getMahasiswa() {
        log.info("Fetching all mahasiswa");
        return mahasiswaRepo.findAll();
    }

    @Override
    public boolean isExist(Long ID) {
        return mahasiswaRepo.existsById(ID);
    }

    //TODO:Custom error response
}
