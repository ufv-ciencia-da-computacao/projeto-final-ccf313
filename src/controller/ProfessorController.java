package controller;

import exceptions.*;
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

    public void addAula(String username, String codDisciplina,
                        double valorHora, List<Integer> topicosStr, String descricao) throws DisciplinaNaoEncontrada, UsuarioNaoEncontradoException {

        List<Topico> topicos = new ArrayList<>();
        for (Integer cod: topicosStr) {
            topicos.add(topicoDAO.getTopico(cod));
        }

        Disciplina dd = disciplinaDAO.getDisciplina(codDisciplina);
        if (dd == null) throw new DisciplinaNaoEncontrada("Disciplina nao encontrada!");

        Professor prof = (Professor) usuarioDAO.getUser(username);
        if (prof == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");

        Aula a = new Aula(topicos, prof, valorHora, dd, descricao);

        aulaDAO.addAula(a);
    }

    public void deleteAula(String username, String codAula) throws UsuarioNaoEncontradoException, AulaNaoEncontrada, UnsafeOperation {
        Professor prof = (Professor) usuarioDAO.getUser(username);
        if (prof == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");

        Aula aulaToDelete = aulaDAO.getAula(codAula);
        if (aulaToDelete == null) throw new AulaNaoEncontrada("Aula nao encontrada!");

        if (aulaToDelete.getProfessor().equals(prof)) {
            aulaDAO.deleteAula(codAula);
        } else {
            throw new UnsafeOperation("Operação nao pode ser realizada!");
        }
    }

    public void negociarContrato(String username, String codContrato, ContratoEtapa etapa) {
       // TODO: Fazer verificação (Exception)
       if (etapa.equals(ContratoEtapa.DECLINADO)) {
           contratoDAO.declineContrato(codContrato);
       } else {
           contratoDAO.acceptContrato(codContrato);
       }
    }

    public List<Contrato> getContratosPendentes(String username) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioDAO.getUser(username);
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");

        return contratoDAO.getAllContratosByProfessorAndEtapa(username, ContratoEtapa.NEGOCIACAO);
    }
}
