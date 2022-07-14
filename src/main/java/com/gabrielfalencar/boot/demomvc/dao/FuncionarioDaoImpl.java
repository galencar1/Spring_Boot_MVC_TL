package com.gabrielfalencar.boot.demomvc.dao;

import com.gabrielfalencar.boot.demomvc.domain.Funcionario;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao{
    @Override
    public List<Funcionario> findByNome(String nome) {

        return createQuery("select f from Funcionario f where f.nome like concat ('%',?1,'%')", nome);
    }
}
