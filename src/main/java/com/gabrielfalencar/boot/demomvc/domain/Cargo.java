package com.gabrielfalencar.boot.demomvc.domain;

import javax.persistence.*;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long>{

    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    @ManyToOne //Muitos cargos para um departamento
    @JoinColumn(name = "id_departamento_fk") // Faz referência ao nome da FK na tabela cargos
    private Departamento departamento; // Faz referência a chave estrangeira (FK) da tabela Departamentos

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
}
