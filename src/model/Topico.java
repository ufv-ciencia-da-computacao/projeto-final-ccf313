package model;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Topico {
    private int id;
    private String descricao;



    public Topico(String descricao) {
        this.id = UUID.randomUUID().hashCode();
        this.descricao = descricao;
    }
    public Topico(int id,String descricao) {
        this(descricao);
        this.id=id;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(descricao, topico.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
