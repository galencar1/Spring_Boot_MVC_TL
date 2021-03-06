package com.gabrielfalencar.boot.demomvc.web.controller;

import com.gabrielfalencar.boot.demomvc.domain.Departamento;
import com.gabrielfalencar.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/departamentos") // Camimho na HOME.html
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @GetMapping("/cadastrar") // caminho na HOME.html
    public String cadastrar(Departamento departamento){
        return "/departamento/cadastro"; // caminho da pasta dentro do resources
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("departamentos", service.buscarTodos());
        return "departamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento, BindingResult result,RedirectAttributes attr){
        if (result.hasErrors()){
            return "/departamento/cadastro";
        }

        service.salvar(departamento);
        attr.addFlashAttribute("success", "Departamento inserido com sucesso!");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("departamento", service.buscaPorId(id));
        return "departamento/cadastro";
    }

    @PostMapping("editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr){
        service.editar(departamento);
        attr.addFlashAttribute("success", "Departamento editado com sucesso!");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir( @PathVariable("id") Long id, ModelMap model ){

        if (service.departamentoTemCargos(id)){
            model.addAttribute("fail", "Departamento n??o removido! Possui cargos vinculados");
        } else {
            service.excluir(id);
            model.addAttribute("success", "Departamento exclu??do com sucesso!");
        }

        return listar(model);
    }
}

