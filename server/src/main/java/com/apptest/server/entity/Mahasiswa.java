package com.apptest.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mahasiswa {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long ID;
    @NotNull
    private String namaDepan;
    private String namaBelakang;
    @NotNull
    private LocalDate tanggalLahir;
}
