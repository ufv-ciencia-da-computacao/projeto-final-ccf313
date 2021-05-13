package persistence.interfaces;

import model.Disciplina;

import java.util.List;

public interface IDisciplinaDAO {
    List<Disciplina> getAllDisciplina();
    Disciplina getDisciplina(String codDiscplina);
}
