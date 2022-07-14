package com.gabrielfalencar.boot.demomvc.web.controller;

import com.gabrielfalencar.boot.demomvc.domain.Cargo;
import com.gabrielfalencar.boot.demomvc.domain.Funcionario;
import com.gabrielfalencar.boot.demomvc.domain.UF;
import com.gabrielfalencar.boot.demomvc.service.CargoService;
import com.gabrielfalencar.boot.demomvc.service.FuncionarioService;
import com.gabrielfalencar.boot.demomvc.web.controller.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private CargoService cargoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new FuncionarioValidator());
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return ("funcionario/cadastro");
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("funcionarios", service.buscarTodos());
        return ("funcionario/lista");
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()){
            return "/funcionario/cadastro";
        }

        service.salvar(funcionario);
        attr.addFlashAttribute("success", "Funcionário inserido com sucesso!");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("funcionario", service.buscarPorId(id));
        return "funcionario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult result,RedirectAttributes attr){
        if (result.hasErrors()){
            return "/funcionario/cadastro";
        }

        service.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionário editado com sucesso");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        service.excluir(id);
        attr.addFlashAttribute("success", "Funcionário removido com sucesso");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap model){
        model.addAttribute("funcionarios", service.buscarPorNome(nome));
        return "funcionario/lista";
    }

    @GetMapping("buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("funcionarios", service.buscarPorCargo(id));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/data")
    public String getPorDatas(@RequestParam(value = "entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                              @RequestParam(value = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
                              ModelMap model) {
        model.addAttribute("funcionarios", service.buscarPorDatas(entrada, saida));
        return "/funcionario/lista";
    }

    @ModelAttribute("cargos")
    public List<Cargo> getCargos(){
        return cargoService.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] getUFs(){
        return UF.values();
    }
}
