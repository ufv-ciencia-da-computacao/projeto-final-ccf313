/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Disciplina;
import persistence.interfaces.IDisciplinaDAO;

/**
 *
 * @author dener
 */
public class DisciplinaController {
    
    IDisciplinaDAO disciplinaDAO;
    
    public DisciplinaController(IDisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }
    
    public List<Disciplina> getAll() {
        return disciplinaDAO.getAllDisciplina();
    }
}
