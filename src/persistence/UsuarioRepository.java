package persistence;

import model.Usuario;

import java.util.ArrayList;

public class UsuarioRepository {
    private DatabaseSingleton instance;

    public UsuarioRepository() {
        this.instance = DatabaseSingleton.getInstance();
    }

    public void addUser(Usuario usuario) {
        instance.usuarios.put(usuario.getUsername(), usuario);
    }

    public Usuario getUser(String username) {
        return instance.usuarios.get(username);
    }

    public ArrayList<Usuario> getAllUser() {
        return new ArrayList<>(instance.usuarios.values());
    }
}
