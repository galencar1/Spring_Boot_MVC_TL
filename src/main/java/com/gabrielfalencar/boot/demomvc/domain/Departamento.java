package com.gabrielfalencar.boot.demomvc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

//Primeiro passo - Estender a Classe Abstract Entity e definir o tipo genérico como LONG.
//Adicionar a Anotação @Entity para que ela seja gerenciada como uma entidade pelo JPA
@Entity
@Table(name = "DEPARTAMENTOS") // Será criada a tabela com nome de DEPARTAMENTOS no banco de dados
public class Departamento extends AbstractEntity <Long>{
    //Gerar atributo nome

    @NotBlank(message = "Informe um nome.")
    @Size(min = 3, max = 60, message = "O Nome do departamento deve ter entre {min} e {max} caracteres")
    @Column(name = "nome", nullable = false, unique = true, length = 60) // Mapeia o atributo nome como uma coluna da tabela DEPARTAMENTOS.
    private String nome;

    @OneToMany (mappedBy = "departamento")// Um Departamento para Muitos Cargos.
    //MappedBy - Define o lado forte da relação(Em relações bidirecionais) Lado departamento é o lado fraco da relação
    //E o lado forte é o Cargo
    private List<Cargo> cargos; // Atributo adicionado pois Departamento terá uma LISTA de cargos

    //Getter e Setter (nome)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    /*
    * Como a Tabela Departamento só possui ID e NOME,
    * criamos apenas o atributo nome, pois o ID já é
    * herdado da Super Classe Abstract Entity.
    * */

    //Criado Getter e Setter do atributo cargo
    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
