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
        instance.usuarios.put(usuario.getUsername(), usuario);
    }

    public Usuario getUser(String email) {
        return instance.usuarios.get(email);
    }

    public ArrayList<Usuario> getAllUser() {
        return new ArrayList<>(instance.usuarios.values());
    }
}
