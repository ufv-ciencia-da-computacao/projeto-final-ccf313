package controller;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import javax.naming.OperationNotSupportedException;
import java.util.Date;
import java.util.List;

public class UsuarioController {
    private IUsuarioDAO usuarioDAO;

    public UsuarioController(IUsuarioDAO usuarioDAO) {
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
}
