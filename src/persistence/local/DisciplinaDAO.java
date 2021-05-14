package persistence.local;

import model.Disciplina;
import persistence.interfaces.IDisciplinaDAO;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO implements IDisciplinaDAO {
    private final LocalDatabaseSingleton localDatabase;

    public DisciplinaDAO() {
        this.localDatabase = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public List<Disciplina> getAllDisciplina() {
        return new ArrayList<Disciplina>(this.localDatabase.disciplinas.values());
    }

    @Override
    public Disciplina getDisciplina(String nome) {
        return this.localDatabase.disciplinas.get(nome);
    }
}
