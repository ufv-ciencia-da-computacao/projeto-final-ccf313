package model;

import java.util.List;
import java.util.UUID;

public class Aula {
    private String codAula;
    private List<Topico> topicos;
    private Disciplina disciplina;
    private Professor professor;
    private double valorHora;
    private String descricao;

    public Aula(List<Topico> topicos, Professor professor, double valorHora, Disciplina disciplina) {
        this.topicos = topicos;
        this.professor = professor;
        this.valorHora = valorHora;
        this.disciplina = disciplina;
        this.codAula= UUID.randomUUID().toString();
    }

    public Aula(List<Topico> topicos, Professor professor, double valorHora, Disciplina disciplina, String descricao) {
        this(topicos, professor, valorHora, disciplina);
        this.descricao = descricao;
        this.codAula=UUID.randomUUID().toString();
    }

    public String getCodAula() {
        return codAula;
    }

    public void setCodAula(String codAula) {
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
