package controller;

import model.Usuario;
import persistence.interfaces.IUsuarioRepository;

import java.util.ArrayList;

public class UsuarioController {
    private IUsuarioRepository usuarioRepository;

    public UsuarioController(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void addUser(Usuario usuario) {
        usuarioRepository.addUser(usuario);
    }



}
