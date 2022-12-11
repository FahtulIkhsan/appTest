package com.apptest.server.repo;

import com.apptest.server.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepo extends JpaRepository<Mahasiswa, Long> {
    Mahasiswa findByID(Long ID);
}
