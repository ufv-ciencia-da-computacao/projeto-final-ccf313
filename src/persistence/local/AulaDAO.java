package persistence.local;

import model.Aula;
import model.Disciplina;
import persistence.interfaces.IAulaDAO;

import java.util.ArrayList;
import java.util.List;

public class AulaDAO implements IAulaDAO {
    private final LocalDatabaseSingleton localDatabase;

    public AulaDAO() {
        this.localDatabase = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public void addAula(Aula aula) {
        localDatabase.aulas.put(aula.getCodAula(), aula);
    }

    @Override
    public void updateAula(Aula aula) {
        localDatabase.aulas.put(aula.getCodAula(), aula);
    }

    @Override
    public Aula getAula(String codAula) {
        return localDatabase.aulas.get(codAula);
    }

    @Override
    public List<Aula> getAulaByDisciplina(Disciplina disciplina) {
        List<Aula> aulas = new ArrayList<Aula>(localDatabase.aulas.values());
        List<Aula> result = new ArrayList<Aula>();
        for (Aula a: aulas) {
            if (a.getDisciplina().equals(disciplina)) {
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public List<Aula> getAllAulas() {
        return new ArrayList<Aula>(localDatabase.aulas.values());
    }

    @Override
    public void deleteAula(String codAula) {
        localDatabase.aulas.remove(codAula);
    }
}
