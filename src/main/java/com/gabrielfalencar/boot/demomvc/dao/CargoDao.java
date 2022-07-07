package com.gabrielfalencar.boot.demomvc.dao;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import com.gabrielfalencar.boot.demomvc.domain.Departamento;

import java.util.List;

public interface CargoDao {

    void save (Cargo cargo);

    void update (Cargo cargo);

    void delete (Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

}
