package controller;

import persistence.interfaces.IContratoDAO;
import persistence.interfaces.IUsuarioDAO;

public class AlunoController {
    IContratoDAO contratoDAO;
    IUsuarioDAO usuarioDAO;

    public AlunoController(IContratoDAO contratoDAO, IUsuarioDAO usuarioDAO) {
        this.contratoDAO = contratoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    void getAulasCompradas() {}
    void getAulasEmNegociacao() {}
    void getAulasDeclinadas() {}
    void solicitarAula() {}
    void verTodasAulasContratadas() {}
}
