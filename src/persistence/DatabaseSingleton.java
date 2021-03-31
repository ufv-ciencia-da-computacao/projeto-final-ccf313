package persistence;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseSingleton {
    private static final DatabaseSingleton DATABASE_INSTANCE = new DatabaseSingleton();
    public HashMap<String, Usuario> usuarios;
    public HashMap<Integer, Aula> aulas;
    public HashMap<Integer, Assunto> assuntos;
    public HashMap<Integer, Disciplina> disciplinas;
    public HashMap<Integer, Contrato> contratos;

    private DatabaseSingleton() {
        usuarios = new HashMap<String, Usuario>();
        aulas = new HashMap<Integer, Aula>();
        assuntos = new HashMap<Integer, Assunto>();
        disciplinas = new HashMap<Integer,Disciplina>();
        contratos = new HashMap<Integer, Contrato>();
    }

    public static DatabaseSingleton getInstance() {
        return DATABASE_INSTANCE;
    }
}
