package model;

import java.util.Date;
import java.util.List;

public class Professor extends Usuario {
    public Professor(String email, String nome, String formacao, Date dataNascimento) {
        super(email, nome, formacao, dataNascimento);
    }
}
