package persistence.interfaces;

import model.Aula;
import model.Professor;

import java.util.List;

public interface IAulaDAO {
    void addAula(Aula aula);
    void updateAula(Aula aula);
    Aula getAula(String codAula);
    List<Aula> getAllAulas();
    void deleteAula(String codAula);
}
