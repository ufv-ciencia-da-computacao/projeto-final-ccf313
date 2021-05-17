package controller;

import exceptions.AulaNaoEncontrada;
import exceptions.DisciplinaNaoEncontrada;
import model.Aula;
import model.Disciplina;
import persistence.interfaces.IAulaDAO;
import persistence.interfaces.IDisciplinaDAO;

import java.util.List;

public class FeedController {
    private IAulaDAO aulaDAO;
    private IDisciplinaDAO disciplinaDAO;

    public FeedController(IAulaDAO aulaDAO, IDisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
        this.aulaDAO = aulaDAO;
    }

    public List<Aula> getAllAulas() {
        return aulaDAO.getAllAulas();
    }

    public Aula getAula(String codAula) throws Exception {
        Aula aula = aulaDAO.getAula(codAula);

        if (aula == null) {
            throw new AulaNaoEncontrada("Aula nao encontrada!");
        }

        return aula;
    }

    public List<Aula> getAulaPorDisciplina(String nome) throws DisciplinaNaoEncontrada {
        Disciplina disciplina = disciplinaDAO.getDisciplina(nome);
        if (disciplina == null) throw new DisciplinaNaoEncontrada("Disciplina nao encontrada!");
        return aulaDAO.getAulaByDisciplina(disciplina.getCodDisciplina());
    }
}
