package com.gabrielfalencar.boot.demomvc.service;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;

import java.util.List;

public interface CargoService {
    void salvar (Cargo cargo);

    void editar (Cargo cargo);

    void excluir (Long id);

    Cargo buscaPorId(Long id);

    List<Cargo> buscarTodos();

    boolean cargoTemFuncionarios(Long id);
}
