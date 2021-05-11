package model;

import java.util.Date;
import java.util.List;

public class Professor extends Usuario {
    public Professor(String username, String nome, String formacao, Date data_nascimento,String descricao,int tipoUsuario) {
        super(username, nome, formacao, data_nascimento,descricao,tipoUsuario);
    }
}
