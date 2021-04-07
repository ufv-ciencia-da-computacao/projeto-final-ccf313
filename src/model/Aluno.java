package model;

import java.util.Date;

public class Aluno extends Usuario{
    public Aluno(String email, String nome, String formacao, Date dataNascimento) {
        super(email, nome, formacao, dataNascimento);
    }
}
