package model;

import java.util.Objects;

public class Usuario {
    protected String username;
    protected String nome;
    protected String descricao;
    protected int idade;
    protected String formacao;
    protected double avaliacao_media;

    public Usuario(String username, String nome, String formacao) {
        this.username = username;
        this.nome = nome;
        this.formacao = formacao;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
