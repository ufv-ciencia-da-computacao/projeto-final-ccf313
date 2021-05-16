package controller;

import exceptions.AulaNaoEncontrada;
import exceptions.ContratoEtapaNaoEncontrada;
import exceptions.ContratoJaExisteException;
import exceptions.UsuarioNaoEncontradoException;
import model.*;
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

    public List<Contrato> getContratosByEtapa(String username, ContratoEtapa etapa) throws ContratoEtapaNaoEncontrada, UsuarioNaoEncontradoException {
        Usuario usuario = usuarioDAO.getUser(username);
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        List<Contrato> contratos = contratoDAO.getAllContratosByAlunoAndEtapa(username, etapa);
        if (contratos == null) throw new ContratoEtapaNaoEncontrada("Não existe contrato com essa etapa!");
        return contratos;
    }

    public void negociarAula(String username, String aulaCod, Date dataComeco, Date dataFinal) throws UsuarioNaoEncontradoException, AulaNaoEncontrada, ContratoJaExisteException {
        Aluno aluno = (Aluno) usuarioDAO.getUser(username);

        if (aluno == null) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }

        Aula aula = aulaDAO.getAula(aulaCod);

        if (aula == null) throw new AulaNaoEncontrada("Aula não encontrada!");
        
        if(contratoDAO.getContratoByAlunoAndAula(username, aulaCod) != null) {
            throw new ContratoJaExisteException();
        }
        
        Contrato contrato = new Contrato(aluno, aula, dataComeco, dataFinal);

        contratoDAO.submitContrato(contrato);
    }
}
