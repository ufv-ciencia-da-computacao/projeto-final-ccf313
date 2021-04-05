package model;

import java.util.Date;
import java.util.Objects;

public class Usuario {
    protected String username;
    protected String nome;
    protected String descricao;
    protected Date data_nascimento;
    protected String formacao;
    protected double avaliacao_media;

    public Usuario(String username, String nome, String formacao, Date data_nascimento) {
        this.username = username;
        this.nome = nome;
        this.formacao = formacao;
        this.data_nascimento = data_nascimento;
        this.avaliacao_media = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public double getAvaliacao() {
        return avaliacao_media;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao_media = avaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return username.equals(usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
