package persistence.interfaces;

import model.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {
     void addUser(Usuario usuario);
     Usuario getUser(String usuario);
     ArrayList<Usuario> getAllUser();
}
