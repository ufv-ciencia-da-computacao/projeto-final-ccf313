package controller;

import model.*;
import persistence.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private IAulaDAO aulaDAO;
    private ITopicoDAO topicoDAO;
    private IUsuarioDAO usuarioDAO;
    private IDisciplinaDAO disciplinaDAO;
    private IContratoDAO contratoDAO;

    public ProfessorController(IAulaDAO aulaDAO, ITopicoDAO topicoDAO,
                               IUsuarioDAO usuarioDAO, IDisciplinaDAO disciplinaDAO,
                               IContratoDAO contratoDAO) {
        this.aulaDAO = aulaDAO;
        this.topicoDAO = topicoDAO;
        this.usuarioDAO = usuarioDAO;
        this.disciplinaDAO = disciplinaDAO;
        this.contratoDAO = contratoDAO;
    }

    public void addAula(String email, String codDisciplina,
                        double valorHora, List<Integer> topicosStr, String descricao) throws Exception {

        List<Topico> topicos = new ArrayList<>();
        for (Integer cod: topicosStr) {
            topicos.add(topicoDAO.getTopico(cod));
        }

        Disciplina dd = disciplinaDAO.getDisciplina(codDisciplina);

        Professor prof = (Professor) usuarioDAO.getUser(email);

        Aula a = new Aula(topicos, prof, valorHora, dd, descricao);

        aulaDAO.addAula(a);
    }

    public void deleteAula(String email, String codAula) throws Exception {
        Professor prof = (Professor) usuarioDAO.getUser(email);

        Aula aulaToDelete = aulaDAO.getAula(codAula);

        if (aulaToDelete == null) {
            throw new Exception("oi");
        }

        if (aulaToDelete.getProfessor().equals(prof)) {
            aulaDAO.deleteAula(codAula);
        } else {
            throw new Exception("oi");
        }
    }

    public void negociarContrato(String email, String codContrato, ContratoEtapa etapa) {
           if (etapa.equals(ContratoEtapa.DECLINADO)) {
               contratoDAO.declineContrato(codContrato);
           } else {
               contratoDAO.acceptContrato(codContrato);
           }
    }

    public List<Contrato> verAulasPorEtapa(String email) {
        return contratoDAO.getAllContratosByProfessorAndEtapa(email, ContratoEtapa.NEGOCIACAO);
    }
}
