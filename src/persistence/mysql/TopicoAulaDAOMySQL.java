package persistence.mysql;

import model.Topico;
import persistence.interfaces.ITopicoAula;
import persistence.interfaces.ITopicoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TopicoAulaDAOMySQL implements ITopicoAula {
    final String SELECT_TOPICOS_POR_COD_AULA = "SELECT * FROM topicoaula WHERE codAula=?;";
    final String DELETE_TOPICO_AULA          = "DELETE FROM topicoaula WHERE codAula=?";
    final String columnIdTopico              = "idTopico";
    final String columnCodAula               = "codAula";
    final String INSERT_TOPICO_AULA          = "INSERT INTO topicoaula(" + columnIdTopico + "," + columnCodAula + ")" +
            " VALUES (?,?);";


    public void addTopicoAula (int idTopico , String codAula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TOPICO_AULA);

            ps.setInt (1 , idTopico);
            ps.setString (2 , codAula);

            int i = ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    @Override
    public ArrayList <Topico> getTopicosByAula (String codAula) {
        Connection         connection = ConnectionFactory.getConnection();
        ArrayList <Topico> topicos    = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement (SELECT_TOPICOS_POR_COD_AULA);
            ps.setObject (1 , codAula);
            ResultSet rs = ps.executeQuery ();

            while (rs.next ()) {
                int    idTopico = rs.getInt ("idTopico");
                Topico topico   = new TopicoDAOMySQL().getTopico(idTopico);

                topicos.add (topico);
            }

            return topicos;

        }
        catch (SQLException ex) {
            ex.printStackTrace ();
        }


        return null;
    }


    @Override
    public void deleteTopicoAula (String codAula) {
        Connection connection = ConnectionFactory.getConnection ();

        try {
            PreparedStatement ps = connection.prepareStatement (DELETE_TOPICO_AULA);
            ps.setString (1 , codAula);

            int i = ps.executeUpdate ();

        }
        catch (SQLException ex) {
            ex.printStackTrace ();
        }

    }
}
