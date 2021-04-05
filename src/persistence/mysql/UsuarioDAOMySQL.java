package persistence.mysql;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import java.util.ArrayList;

public class UsuarioDAOMySQL implements IUsuarioDAO {
    @Override
    public void addUser(Usuario usuario) { }

    @Override
    public Usuario getUser(String username) {
        return null;
    }

    @Override
    public ArrayList<Usuario> getAllUser() {
        return null;
    }
}
