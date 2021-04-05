package persistence;

import model.*;

import java.util.HashMap;

public class LocalDatabaseSingleton {
    private static final LocalDatabaseSingleton DATABASE_INSTANCE = new LocalDatabaseSingleton();
    public HashMap<String, Usuario> usuarios;
    public HashMap<Integer, Aula> aulas;
    public HashMap<Integer, Topico> topicos;
    public HashMap<Integer, Disciplina> disciplinas;
    public HashMap<Integer, Contrato> contratos;

    private LocalDatabaseSingleton() {
        usuarios = new HashMap<String, Usuario>();
        aulas = new HashMap<Integer, Aula>();
        topicos = new HashMap<Integer, Topico>();
        disciplinas = new HashMap<Integer,Disciplina>();
        contratos = new HashMap<Integer, Contrato>();
    }

    public static LocalDatabaseSingleton getInstance() {
        return DATABASE_INSTANCE;
    }
}
