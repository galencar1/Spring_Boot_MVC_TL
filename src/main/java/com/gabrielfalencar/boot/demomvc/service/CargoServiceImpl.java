package com.gabrielfalencar.boot.demomvc.service;

import com.gabrielfalencar.boot.demomvc.dao.CargoDao;
import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = false)
public class CargoServiceImpl implements CargoService{
    @Autowired
    private CargoDao dao;


    @Override
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo buscaPorId(Long id) {

        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean cargoTemFuncionarios(Long id) {
        if(buscaPorId(id).getFuncionarios().isEmpty()){
            return false;
        }
        return true;
    }
}
