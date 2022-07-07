package com.gabrielfalencar.boot.demomvc.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long>{

    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    @ManyToOne //Muitos cargos para um departamento
    @JoinColumn(name = "id_departamento_fk") // Faz referência ao nome da FK na tabela cargos
    private Departamento departamento; // Faz referência a chave estrangeira (FK) da tabela Departamentos

    //Mapeando os funcionários
    @OneToMany(mappedBy = "cargo") // A Tabela que tem a FK é o  lado forte. No mappedBy declaramos o lado fraco
    private List<Funcionario> funcionarios;

//    Getter e Setter para nome e departamento
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
