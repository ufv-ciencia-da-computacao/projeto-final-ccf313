package persistence.mysql;

import model.Disciplina;
import persistence.interfaces.IDisciplinaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOMySQL implements IDisciplinaDAO {
    final String SELECT_DISCIPLINA = "SELECT * FROM disciplina WHERE codDisciplina=?;";
    final String SELECT_ALL_USERS  = "SELECT * FROM disciplina;";

    final String columnNome          = "nome";
    final String columnDescricao     = "descricao";
    final String columnCodDisciplina = "codDisciplina";

    @Override
    public List <Disciplina> getAllDisciplina () {
        Connection             connection  = ConnectionFactory.getConnection ();
        ArrayList <Disciplina> disciplinas = new ArrayList <> ();
        try {
            PreparedStatement ps = connection.prepareStatement (SELECT_ALL_USERS);

            ResultSet rs = ps.executeQuery ();


            while (rs.next ()) {

                String nome = rs.getNString (columnNome);
                String descricao     = rs.getNString (columnDescricao);
                String codDisciplina = rs.getNString (columnCodDisciplina);

                Disciplina disciplina = new Disciplina (codDisciplina,nome , descricao);

                disciplinas.add (disciplina);

            }

            return disciplinas;

        } catch (SQLException ex) {
            ex.printStackTrace ();
        }

        return null;
    }

    @Override
    public Disciplina getDisciplina (String codDisciplina) {
        Connection connection = ConnectionFactory.getConnection ();
        try {
            PreparedStatement ps = connection.prepareStatement (SELECT_DISCIPLINA);
            ps.setString (1 , codDisciplina);
            ResultSet rs = ps.executeQuery ();

            if (rs.next ()) {
                String nome          = rs.getNString (columnNome);
                String descricao     = rs.getNString (columnDescricao);


                return new Disciplina (codDisciplina , nome , descricao);
            }


        } catch (SQLException ex) {
            ex.printStackTrace ();
        }


        return null;
    }


}
