package com.gabrielfalencar.boot.demomvc.web.controller.conversor;

import com.gabrielfalencar.boot.demomvc.domain.Departamento;
import com.gabrielfalencar.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter <String, Departamento> {

    @Autowired
    private DepartamentoService service;

    @Override
    public Departamento convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text) ;
        return service.buscaPorId(id);
    }
}
