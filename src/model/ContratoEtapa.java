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

    public static ContratoEtapa identificarPorId(int id){
        if (id==0){
            return ContratoEtapa.NEGOCIACAO;
        }else if(id==1){
            return ContratoEtapa.ACEITO;
        }else{
            return ContratoEtapa.DECLINADO;
        }
    }
}
