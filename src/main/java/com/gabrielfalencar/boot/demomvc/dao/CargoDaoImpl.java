package com.gabrielfalencar.boot.demomvc.dao;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import org.springframework.stereotype.Repository;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{
}
