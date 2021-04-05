package persistence.interfaces;

import model.Aula;

import java.util.List;

public interface IAulaDAO {
    void addAula(Aula aula);
//    void updateAula(int cod_aula, Aula aula);
    Aula getAula(int codAula);
    List<Aula> getAllAulas();
    void deleteAula(int codAula);
}
