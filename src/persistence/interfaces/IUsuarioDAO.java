package persistence.interfaces;

import model.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {
    public void addUser(Usuario usuario);
    public Usuario getUser(String username);
    public ArrayList<Usuario> getAllUser();
}
