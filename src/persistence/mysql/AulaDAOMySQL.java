package persistence.mysql;

import model.*;
import persistence.interfaces.IAulaDAO;
import persistence.interfaces.ITopicoAula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDAOMySQL implements IAulaDAO {
    final String columnCodAula = "codAula";
    final String columnvalorHora = "valorHora";
    final String columnDescricao = "descricao";
    final String columnCodDisciplina = "codDisciplina";
    final String columnUsernameProfessor = "usernameProfessor";


    final String INSERT_AULA = "INSERT INTO aula(" + columnCodAula + "," + columnvalorHora + "," +
            columnDescricao + "," + columnCodDisciplina + "," + columnUsernameProfessor + ") VALUES (?,?,?,?,?);";

    final String SELECT_POR_DISCIPLINA = "SELECT * FROM aula WHERE codDisciplina=?;";
    final String SELECT_POR_COD_AULA = "SELECT * FROM aula WHERE codAula=?;";
    final String SELECT_ALL_AULAS = "SELECT * FROM aula ;";

    final String UPDATE_AULA = ("UPDATE aula SET valorHora=?, descricao=?, codDisciplina=?, usernameProfessor=? WHERE codAula=?");

    final String DELETE_AULA = "DELETE FROM aula WHERE codAula=?";


    @Override
    public void addAula ( Aula aula ) {
        Connection connection = ConnectionFactory.getConnection ( );
        TopicoAulaDAOMySQL topicoAulaDAOMySQL = new TopicoAulaDAOMySQL ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( INSERT_AULA );

            ps.setString ( 1 , aula.getCodAula ( ) );
            ps.setDouble ( 2 , aula.getValorHora ( ) );
            ps.setString ( 3 , aula.getDescricao ( ) );
            ps.setString ( 4 , aula.getDisciplina ( ).getCodDisciplina ( ) );
            ps.setString ( 5 , aula.getProfessor ( ).getUsername ( ) );

            int i = ps.executeUpdate ( );
            for ( int k = 0 ; k < aula.getTopicos ( ).size ( ) ; k++ ) {
                topicoAulaDAOMySQL.addTopicoAula ( aula.getTopicos ( ).get ( i ).getId ( ) , aula.getCodAula ( ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }


    }

    @Override
    public void updateAula ( Aula aula ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( UPDATE_AULA );
            ps.setDouble ( 1 , aula.getValorHora ( ) );
            ps.setString ( 2 , aula.getDescricao ( ) );
            ps.setString ( 3 , aula.getDisciplina ( ).getCodDisciplina ( ) );
            ps.setString ( 4 , aula.getProfessor ( ).getUsername ( ) );
            ps.setString ( 5 , aula.getCodAula ( ) );
            ps.executeUpdate ( );

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


    }

    @Override
    public Aula getAula ( String codAula ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_POR_COD_AULA );
            ps.setObject ( 1 , codAula );
            ResultSet rs = ps.executeQuery ( );
            UsuarioDAOMySQL usuarioDAOMySQL = new UsuarioDAOMySQL ( );
            DisciplinaDAOMySQL disciplinaDAOMySQL = new DisciplinaDAOMySQL ( );
            ITopicoAula topicoAulaDAOMySQL = new TopicoAulaDAOMySQL ( );


            if ( rs.next ( ) ) {

                String usernameProfessor = rs.getNString ( columnUsernameProfessor );
                double valorHora = rs.getDouble ( columnvalorHora );
                String descricao = rs.getNString ( columnDescricao );
                String codDisciplina = rs.getNString ( columnCodDisciplina );

                Usuario userProfessor = usuarioDAOMySQL.getUser ( usernameProfessor );
                Disciplina disciplina = disciplinaDAOMySQL.getDisciplina ( codDisciplina );

                Professor professor = new Professor ( userProfessor.getUsername ( ) , userProfessor.getNome ( ) , userProfessor.getFormacao ( ) ,
                        userProfessor.getDataNascimento ( ) , userProfessor.getDescricao ( ) , userProfessor.getTipoUsuario ( ) );

                ArrayList < Topico > topicos = topicoAulaDAOMySQL.getTopicosByAula ( codAula );
                return new Aula ( codAula , topicos , professor , valorHora , disciplina , descricao );
            }


        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public List < Aula > getAulaByDisciplina ( String codDisciplina ) {
        Connection connection = ConnectionFactory.getConnection ( );
        ArrayList < Aula > aulas = new ArrayList <> ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_POR_DISCIPLINA );
            ps.setObject ( 1 , codDisciplina );
            ResultSet rs = ps.executeQuery ( );
            UsuarioDAOMySQL usuarioDAOMySQL = new UsuarioDAOMySQL ( );
            DisciplinaDAOMySQL disciplinaDAOMySQL = new DisciplinaDAOMySQL ( );
            TopicoAulaDAOMySQL topicoAulaDAOMySQL = new TopicoAulaDAOMySQL ( );


            while (rs.next ( )) {

                String usernameProfessor = rs.getNString ( columnUsernameProfessor );
                double valorHora = rs.getDouble ( columnvalorHora );
                String descricao = rs.getNString ( columnDescricao );
                String codAula = rs.getNString ( columnCodAula );

                Usuario userProfessor = usuarioDAOMySQL.getUser ( usernameProfessor );
                Disciplina disciplina = disciplinaDAOMySQL.getDisciplina ( codDisciplina );


                Professor professor = new Professor ( userProfessor.getUsername ( ) ,
                        userProfessor.getNome ( ) ,
                        userProfessor.getFormacao ( ) ,
                        userProfessor.getDataNascimento ( ) ,
                        userProfessor.getDescricao ( ) ,
                        userProfessor.getTipoUsuario ( ) );

                ArrayList < Topico > topicos = topicoAulaDAOMySQL.getTopicosByAula ( codAula );
                Aula aula = new Aula ( codAula ,
                        topicos ,
                        professor ,
                        valorHora ,
                        disciplina ,
                        descricao );

                aulas.add ( aula );

            }
            return aulas;


        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public List < Aula > getAllAulas ( ) {
        Connection connection = ConnectionFactory.getConnection ( );
        ArrayList < Aula > aulas = new ArrayList <> ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_ALL_AULAS );

            ResultSet rs = ps.executeQuery ( );

            while (rs.next ( )) {
                String codAula = rs.getNString ( columnCodAula );
                Aula aula = getAula ( codAula );


                aulas.add ( aula );
                System.out.println ( aulas.size ( ) );

            }

            return aulas;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public void deleteAula ( String codAula ) {
        Connection connection = ConnectionFactory.getConnection ( );
        TopicoAulaDAOMySQL topicoAulaDAOMySQL = new TopicoAulaDAOMySQL ( );

        try {

            PreparedStatement ps = connection.prepareStatement ( DELETE_AULA );
            ps.setString ( 1 , codAula );
            topicoAulaDAOMySQL.deleteTopicoAula ( codAula );

             ps.executeUpdate ( );


        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }

    }
}
