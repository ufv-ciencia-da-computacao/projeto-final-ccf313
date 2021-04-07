package model;

public class Disciplina {
    private String codDisciplina;
    private String nome;
    private String descricao;

    public Disciplina(String codDisciplina, String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.codDisciplina = codDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
