package controller;

import exceptions.UsernameNaoUnico;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.UsuarioSemAvaliacao;
import model.Avaliacao;
import model.Usuario;
import persistence.interfaces.IAvaliacaoDAO;
import persistence.interfaces.IUsuarioDAO;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioController {
    private IUsuarioDAO usuarioDAO;
    private IAvaliacaoDAO avaliacaoDAO;

    public UsuarioController(IUsuarioDAO usuarioDAO, IAvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public void addUser(String username, String nome, String formacao, Date data_nascimento) throws UsernameNaoUnico {
        try {
            getUser(username);
            throw new UsernameNaoUnico("Um usuario já foi cadastrado com o mesmo username!");
        } catch(UsuarioNaoEncontradoException e) {
            usuarioDAO.addUser(new Usuario(username, nome, formacao, data_nascimento));
        }
    }

    public Usuario getUser(String username) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioDAO.getUser(username);
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
        return usuario;
    }

    public List<Usuario> getAllUsers() {
        return usuarioDAO.getAllUser();
    }

    public void avaliarAluno(String usernameAvaliador, String usernameAvaliado, double valor, String comentario) throws UsuarioNaoEncontradoException {
        Usuario avaliado = usuarioDAO.getUser(usernameAvaliado);
        Usuario avaliador = usuarioDAO.getUser(usernameAvaliador);

        if (avaliado == null || avaliador == null) {
            throw new UsuarioNaoEncontradoException("Usuário Avaliado ou Avaliador não encontrado!");
        }

        Avaliacao avaliacao = new Avaliacao(avaliador, avaliado, valor, comentario);
        avaliacaoDAO.addAvaliacao(avaliacao);
    }

    public List<Avaliacao> getAvaliacoesByUsuario(String usernameAvaliado) throws UsuarioSemAvaliacao, UsuarioNaoEncontradoException {
        Usuario usuario = usuarioDAO.getUser(usernameAvaliado);
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");

        List<Avaliacao> avaliacoes = avaliacaoDAO.getByUsuario(usernameAvaliado);
        if (avaliacoes.isEmpty()) throw new UsuarioSemAvaliacao("Usuario não possui nenhuma avalaiação!");

        return avaliacaoDAO.getByUsuario(usernameAvaliado);
    }

    public double getMediaAvaliacoes(String usernameAvaliado) throws UsuarioSemAvaliacao, UsuarioNaoEncontradoException {
        Usuario usuario = usuarioDAO.getUser(usernameAvaliado);
        if (usuario == null) throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");

        List<Avaliacao> avaliacoes = avaliacaoDAO.getByUsuario(usernameAvaliado);
        double media = 0;

        if (avaliacoes.isEmpty()) throw new UsuarioSemAvaliacao("Usuario não possui nenhuma avalaiação!");

        for (Avaliacao a: avaliacoes) {
            media += a.getValor();
        }

        return media/avaliacoes.size();
    }

}
