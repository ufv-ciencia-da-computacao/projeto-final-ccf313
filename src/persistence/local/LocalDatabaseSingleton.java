package persistence.local;

import java.util.ArrayList;
import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        mockTopico();
        mockDisciplina();
        mockUsuarios();
        mockAulas();
    }
    
    private void mockTopico() {
        Topico topico1 = new Topico("ensino médio");
        Topico topico2 = new Topico("cálculo");
        Topico topico3 = new Topico("física elétrica");
        Topico topico4 = new Topico("quântica");
        
        topicos.put(topico1.getId(), topico1);
        topicos.put(topico2.getId(), topico2);
        topicos.put(topico3.getId(), topico3);
        topicos.put(topico4.getId(), topico4);
    }
    
    private void mockDisciplina() {
        Disciplina est = new Disciplina("Estatística", "");
        Disciplina mat = new Disciplina("Cálculo 1", "Limites, Derivadas e Integrais");
        Disciplina fis = new Disciplina("Física", "Qualquer tópico de física");
        
        disciplinas.put(est.getNome(), est);
        disciplinas.put(mat.getNome(), mat);
        disciplinas.put(fis.getNome(), fis);
    }
    
    private void mockUsuarios() {
        Aluno aluno = new Aluno("gegen07", "Germano", "Graduando", new java.util.Date(2001-1900,8,5), "");
        Professor professor = new Professor("dener", "Dener", "Graduando", new java.util.Date(2001-1900,8,5), "");
        
        usuarios.put(aluno.getUsername(), aluno);
        usuarios.put(professor.getUsername(), professor);
    }
    
    private void mockAulas() {
        List<Topico> top = new ArrayList<Topico>();
        for (Map.Entry<Integer, Topico> pair : this.topicos.entrySet()) {
            if (pair.getValue().getDescricao().equals("cálculo")) {
                top.add(pair.getValue());
            }
        }
        Aula aula = new Aula(top, (Professor) usuarios.get("dener"), 30.5, disciplinas.get("Cálculo 1"), "");
        aulas.put(aula.getCodAula(), aula);
    }

    public static LocalDatabaseSingleton getInstance() {
        return DATABASE_INSTANCE;
    }
}
