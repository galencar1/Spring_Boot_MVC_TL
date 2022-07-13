package com.gabrielfalencar.boot.demomvc.web.controller;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import com.gabrielfalencar.boot.demomvc.domain.Departamento;
import com.gabrielfalencar.boot.demomvc.service.CargoService;
import com.gabrielfalencar.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo){
        return "cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", service.buscarTodos());
        return "cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar (Cargo cargo, RedirectAttributes attr){
        service.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso!");
        return "redirect:/cargos/cadastrar";
    }

    //Lista todos os departamentos cadastrado no combo-box da página de cargos;
    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        return departamentoService.buscarTodos();
    }
}
