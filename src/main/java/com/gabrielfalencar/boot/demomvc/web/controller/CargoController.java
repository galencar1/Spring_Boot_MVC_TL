package com.gabrielfalencar.boot.demomvc.web.controller;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import com.gabrielfalencar.boot.demomvc.domain.Departamento;
import com.gabrielfalencar.boot.demomvc.service.CargoService;
import com.gabrielfalencar.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String salvar (@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) {
            return "/cargo/cadastro";
        }

        service.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso!");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("cargo", service.buscaPorId(id));
        return "cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result,RedirectAttributes attr){
        service.editar(cargo);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso!");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,  RedirectAttributes attr){
        if (service.cargoTemFuncionarios(id)) {
            attr.addFlashAttribute("fail", "Cargo n??o exclu??do! Tem funcion??rio(s) vinculado(s)");
        }else {
            service.excluir(id);
            attr.addFlashAttribute("success", "Cargo exclu??do com sucesso!");
        }
        return "redirect:/cargos/listar";
    }

    //Lista todos os departamentos cadastrado no combo-box da p??gina de cargos;
    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        return departamentoService.buscarTodos();
    }
}
