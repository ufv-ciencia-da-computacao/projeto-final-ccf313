package controller;

import model.Aluno;
import model.Aula;
import model.Contrato;
import model.ContratoEtapa;
import persistence.interfaces.IAulaDAO;
import persistence.interfaces.IContratoDAO;
import persistence.interfaces.IUsuarioDAO;

import java.util.Date;
import java.util.List;

public class AlunoController {
    IContratoDAO contratoDAO;
    IUsuarioDAO usuarioDAO;
    IAulaDAO aulaDAO;

    public AlunoController(IContratoDAO contratoDAO, IUsuarioDAO usuarioDAO, IAulaDAO aulaDAO) {
        this.contratoDAO = contratoDAO;
        this.usuarioDAO = usuarioDAO;
        this.aulaDAO = aulaDAO;
    }

    List<Contrato> getContratosByEtapa(String email, ContratoEtapa etapa) {
        return contratoDAO.getAllContratosByAlunoAndEtapa(email, etapa);
    }

    void negociarAula(String email, String aulaCod, Date dataComeco, Date dataFinal) {
        Aluno aluno = (Aluno) usuarioDAO.getUser(email);
        Aula aula = aulaDAO.getAula(aulaCod);

        Contrato contrato = new Contrato(aluno, aula, dataComeco, dataFinal);

        contratoDAO.submitContrato(contrato);
    }
}
