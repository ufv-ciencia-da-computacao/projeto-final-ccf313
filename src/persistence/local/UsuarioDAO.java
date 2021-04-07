package persistence.local;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import java.util.ArrayList;

public class UsuarioDAO implements IUsuarioDAO {
    private LocalDatabaseSingleton instance;

    public UsuarioDAO() {
        this.instance = LocalDatabaseSingleton.getInstance();
    }

    public void addUser(Usuario usuario) {
        instance.usuarios.put(usuario.getEmail(), usuario);
    }

    public Usuario getUser(String username) {
        return instance.usuarios.get(username);
    }

    public ArrayList<Usuario> getAllUser() {
        return new ArrayList<>(instance.usuarios.values());
    }
}
