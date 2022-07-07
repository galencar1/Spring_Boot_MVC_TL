package com.gabrielfalencar.boot.demomvc.domain;

import javax.persistence.*;

//Primeiro passo - Estender a Classe Abstract Entity e definir o tipo genérico como LONG.
//Adicionar a Anotação @Entity para que ela seja gerenciada como uma entidade pelo JPA
@Entity
@Table(name = "DEPARTAMENTOS") // Será criada a tabela com nome de DEPARTAMENTOS no banco de dados
public class Departamento extends AbstractEntity <Long>{
    //Gerar atributo nome
    @Column(name = "nome", nullable = false, unique = true, length = 60) // Mapeia o atributo nome como uma coluna da tabela DEPARTAMENTOS.
    private String nome;

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
}
