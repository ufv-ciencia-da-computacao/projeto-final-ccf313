package model;

import java.util.Random;
import java.util.UUID;

public class Topico {
    private int id;
    private String descricao;

    public Topico(String descricao) {
        this.id = UUID.randomUUID().variant();
        this.descricao = descricao;
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
}
