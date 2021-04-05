package controller;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import javax.naming.OperationNotSupportedException;
import java.util.Date;
import java.util.List;

public class UsuarioController {
    private IUsuarioDAO usuarioDao;

    public UsuarioController(IUsuarioDAO usuarioRepository) {
        this.usuarioDao = usuarioRepository;
    }

    public void addUser(String username, String nome, String formacao, Date data_nascimento) throws Exception {
        if (getUser(username) != null) {
            usuarioDao.addUser(new Usuario(username, nome, formacao, data_nascimento));
        } else {
            throw new Exception("oi");
        }
    }

    public void updateUser() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public Usuario getUser(String username) {
        return usuarioDao.getUser(username);
    }

    public List<Usuario> getAllUsers() {
        return usuarioDao.getAllUser();
    }
}
