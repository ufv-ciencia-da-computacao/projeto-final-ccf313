package controller;

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

    public void addUser(String username, String nome, String formacao, Date data_nascimento) throws Exception {
        if (getUser(username) != null) {
            usuarioDAO.addUser(new Usuario(username, nome, formacao, data_nascimento));
        } else {
            throw new Exception("oi");
        }
    }

    public void updateUser() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public Usuario getUser(String username) {
        return usuarioDAO.getUser(username);
    }

    public List<Usuario> getAllUsers() {
        return usuarioDAO.getAllUser();
    }

    public void avaliarAluno(String usernameAvaliador, String usernameAvaliado, double valor, String comentario) {
        Usuario avaliado = usuarioDAO.getUser(usernameAvaliado);
        Usuario avaliador = usuarioDAO.getUser(usernameAvaliador);

        Avaliacao avaliacao = new Avaliacao(avaliador, avaliado, valor, comentario);
        avaliacaoDAO.addAvaliacao(avaliacao);
    }

    public List<Avaliacao> getAvaliacoesByUsuario(String usernameAvaliado) {
        return avaliacaoDAO.getByUsuario(usernameAvaliado);
    }

    public double getMediaAvaliacoes(String usernameAvaliado) {
        List<Avaliacao> avaliacoes = avaliacaoDAO.getByUsuario(usernameAvaliado);

        double media = 0;

        for (Avaliacao a: avaliacoes) {
            media += a.getValor();
        }

        return media/avaliacoes.size();
    }

}
