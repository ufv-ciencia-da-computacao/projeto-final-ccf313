package model;

import java.util.Date;

public class Aluno extends Usuario{
    public Aluno(String username, String nome, String formacao, Date data_nascimento,String descricao) {
        super(username, nome, formacao, data_nascimento,descricao, 0);
    }
}
