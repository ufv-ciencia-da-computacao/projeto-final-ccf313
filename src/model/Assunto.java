package model;

public class Assunto {
    private Disciplina disciplina;
    private String descricao;

    public Assunto(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Assunto(Disciplina disciplina, String descricao) {
        this(disciplina);
        this.descricao = descricao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
