package model;

import java.util.Objects;
import java.util.UUID;

public class Disciplina {
    private String codDisciplina;
    private String nome;
    private String descricao;

    public Disciplina(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.codDisciplina = UUID.randomUUID().toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(codDisciplina, that.codDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDisciplina);
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
