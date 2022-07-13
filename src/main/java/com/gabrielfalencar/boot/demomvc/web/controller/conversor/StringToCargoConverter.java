package com.gabrielfalencar.boot.demomvc.web.controller.conversor;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import com.gabrielfalencar.boot.demomvc.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToCargoConverter implements Converter <String, Cargo> {

    @Autowired
    CargoService service;

    @Override
    public Cargo convert(String text) {
        if (text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscaPorId(id);
    }
}
