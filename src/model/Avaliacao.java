package model;

public class Avaliacao {
    private Usuario avaliador;
    private Usuario avaliado;
    private double valor;
    private String comentario;

    public Avaliacao(Usuario avaliador, Usuario avaliado, double valor) {
        this.avaliado = avaliado;
        this.avaliador = avaliador;
        this.valor = valor;
    }

    public Avaliacao(Usuario avaliador, Usuario avaliado, double valor, String comentario) {
        this.avaliador = avaliador;
        this.avaliado = avaliado;
        this.valor = valor;
        this.comentario = comentario;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
