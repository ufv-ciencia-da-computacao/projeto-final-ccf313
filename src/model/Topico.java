package model;

public class Topico {
    private int id;
    private String descricao;



    public Topico(String descricao) {
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
}
