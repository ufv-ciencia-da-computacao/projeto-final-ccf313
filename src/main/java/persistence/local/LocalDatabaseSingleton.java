package persistence.local;

import model.*;

import java.util.HashMap;
import java.util.List;

public class LocalDatabaseSingleton {
    private static final LocalDatabaseSingleton DATABASE_INSTANCE = new LocalDatabaseSingleton();
    public HashMap<String, Usuario> usuarios;
    public HashMap<String, Aula> aulas;
    public HashMap<Integer, Topico> topicos;
    public HashMap<String, Disciplina> disciplinas;
    public HashMap<String, Contrato> contratos;
    public HashMap<String, List<Avaliacao>> avaliacoes;

    private LocalDatabaseSingleton() {
        usuarios = new HashMap<String, Usuario>();
        aulas = new HashMap<String, Aula>();
        topicos = new HashMap<Integer, Topico>();
        disciplinas = new HashMap<String,Disciplina>();
        contratos = new HashMap<String, Contrato>();
        avaliacoes = new HashMap<String, List<Avaliacao>>();
    }

    public static LocalDatabaseSingleton getInstance() {
        return DATABASE_INSTANCE;
    }
}
