package controller;

import model.Aula;
import persistence.interfaces.IAulaDAO;

import java.util.List;

public class FeedController {
    private IAulaDAO aulaDAO;

    public FeedController(IAulaDAO aulaDAO) {
        this.aulaDAO = aulaDAO;
    }

    public List<Aula> getAllAulas() {
        return aulaDAO.getAllAulas();
    }

    public Aula getAula(String codAula) throws Exception {
        Aula aula = aulaDAO.getAula(codAula);

        if (aula == null) {
            throw new Exception("oi");
        } else {
            aulaDAO.getAula(codAula);
        }

        return aula;
    }
}
