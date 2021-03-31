package model;

public class Aula {
    private Assunto assunto;
    private Professor professor;
    private double valor_hora;
    private String descricao;

    public Aula(Assunto assunto, Professor professor, double valor_hora) {
        this.assunto = assunto;
        this.professor = professor;
        this.valor_hora = valor_hora;
    }

    public Aula(Assunto assunto, Professor professor, double valor_hora, String descricao) {
        this(assunto, professor, valor_hora);
        this.descricao = descricao;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public double getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(double valor_hora) {
        this.valor_hora = valor_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
