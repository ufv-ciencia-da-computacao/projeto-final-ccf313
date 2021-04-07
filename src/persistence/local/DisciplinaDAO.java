package persistence.local;

import model.Disciplina;
import persistence.interfaces.IDisciplinaDAO;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO implements IDisciplinaDAO {
    private LocalDatabaseSingleton localDatabase;

    public DisciplinaDAO(LocalDatabaseSingleton localDatabase) {
        this.localDatabase = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public List<Disciplina> getAllDisciplina() {
        return new ArrayList<Disciplina>(this.localDatabase.disciplinas.values());
    }

    @Override
    public Disciplina getDisciplina(String codDiscplina) {
        return this.localDatabase.disciplinas.get(codDiscplina);
    }
}
