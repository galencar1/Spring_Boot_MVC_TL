package com.gabrielfalencar.boot.demomvc.domain;
/*
** Classe responsável por repassar
* todas as suas características para as demais.
* Responsável por armazenar um ID e os métodos GET E SET
* To string, Equals e HashCode.
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@MappedSuperclass
public abstract class AbstractEntity <ID extends Serializable> implements Serializable{
//Primeiro passo - Definir tipo genérico para que a implementação das classes filhas
// sejam responsáveis por dizer qual id vamos utilizar. <ID extends Serializable>
// Após isso implementamos o Serializable para que todas as classes filhas recebam a implementação. implements Serializable

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    //Métodos Getter e Setter
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    //Métodos Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Método Tostring
    @Override
    public String toString() {
        return "id = " + id;
    }
}
