package com.gabrielfalencar.boot.demomvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos") // Camimho na HOME.html
public class DepartamentoController {

    @GetMapping("/cadastrar") // caminho na HOME.html
    public String cadastrar(){
        return "/departamento/cadastro"; // caminho da pasta dentro do resources
    }

    @GetMapping("/listar")
    public String listar(){
        return "departamento/lista";
    }
}

