package model;

public enum ContratoEtapa {
    NEGOCIACAO(0), ACEITO(1), DECLINADO(2);

    private final int id;
    ContratoEtapa(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
