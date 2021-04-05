package model;

import java.util.List;

public class Aula {
    private int codAula;
    private List<Topico> topicos;
    private Disciplina disciplina;
    private Professor professor;
    private double valorHora;
    private String descricao;

    public Aula(List<Topico> topicos, Professor professor, double valorHora) {
        this.topicos = topicos;
        this.professor = professor;
        this.valorHora = valorHora;
    }

    public Aula(List<Topico> topicos, Professor professor, double valorHora, String descricao) {
        this(topicos, professor, valorHora);
        this.descricao = descricao;
    }

    public int getCodAula() {
        return codAula;
    }

    public void setCodAula(int codAula) {
        this.codAula = codAula;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topico) {
        this.topicos = topico;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public double getValor_hora() {
        return valorHora;
    }

    public void setValor_hora(double valor_hora) {
        this.valorHora = valor_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
