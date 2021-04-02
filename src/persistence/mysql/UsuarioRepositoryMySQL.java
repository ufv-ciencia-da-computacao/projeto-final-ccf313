package persistence.mysql;

import model.Usuario;
import persistence.interfaces.IUsuarioRepository;

import java.util.ArrayList;

public class UsuarioRepositoryMySQL implements IUsuarioRepository {
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
