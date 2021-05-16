package persistence.mysql;

import model.*;
import persistence.interfaces.IContratoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAOMySQL implements IContratoDAO {
    final String columnUsernameAluno  = "usernameAluno";
    final String columnCodAula        = "codAula";
    final String columnCodContrato    = "codContrato";
    final String columnDataFinal      = "dataFinal";
    final String columnDataInicio     = "dataInicio";
    final String columnStatusContrato = "statusContrato";


    final String INSERT_CONTRATO_AULA = "INSERT INTO contratoaula(" + columnUsernameAluno + "," + columnCodAula + "," +
            columnCodContrato + "," + columnDataFinal + "," + columnDataInicio + "," + columnStatusContrato + ") VALUES (?,?,?,?,?,?);";

    final String SELECT_ALL_POR_PROFESSOR = "SELECT usernameAluno,CA.codAula,codContrato,dataFinal,dataInicio,statusContrato\n" +
            "FROM contratoaula AS CA \n" +
            "JOIN aula AS A ON CA.codAula=A.codAula\n" +
            "WHERE usernameProfessor=?;";

    final String SELECT_ALL_POR_ALUNO               = "SELECT usernameAluno,codAula,codContrato,dataFinal,dataInicio,statusContrato\n" +
            "FROM contratoaula WHERE usernameAluno=?;";
    final String SELECT_ALL_POR_ALUNO_AND_ETAPA     = "SELECT * FROM contratoaula " +
            "WHERE usernameAluno=? AND statusContrato=?;";
    final String SELECT_ALL_POR_PROFESSOR_AND_ETAPA = "SELECT usernameAluno,CA.codAula," +
            "codContrato,dataFinal,dataInicio,statusContrato\n" +
            "FROM contratoaula AS CA \n" +
            "JOIN aula AS A ON CA.codAula=A.codAula\n" +
            "WHERE usernameProfessor=? AND statusContrato=?;";

    final String UPDATE_STATUS_CONTRATO = ("UPDATE contratoaula SET statusContrato=? WHERE codContrato=?");


    @Override
    public void submitContrato ( Contrato contrato ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( INSERT_CONTRATO_AULA );

            ps.setString ( 1 , contrato.getAluno ( ).getUsername ( ) );
            ps.setString ( 2 , contrato.getAula ( ).getCodAula ( ) );
            ps.setString ( 3 , contrato.getCodContrato ( ) );
            ps.setDate ( 4 , (Date) contrato.getDataFinal ( ) );
            ps.setDate ( 5 , (Date) contrato.getDataComeco ( ) );
            ps.setInt ( 6 , contrato.getEtapas ( ).getId ( ) );
            ps.executeUpdate ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    @Override
    public void acceptContrato ( String codContrato ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( UPDATE_STATUS_CONTRATO );
            ps.setDouble ( 1 , ContratoEtapa.ACEITO.getId ( ) );
            ps.setString ( 2 , codContrato );

           ps.executeUpdate ( );

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }

    }

    @Override
    public void declineContrato ( String codContrato ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( UPDATE_STATUS_CONTRATO );
            ps.setDouble ( 1 , ContratoEtapa.DECLINADO.getId ( ) );
            ps.setString ( 2 , codContrato );

         ps.executeUpdate( );

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }

    }

    @Override
    public List < Contrato > getAllContratosByProfessor ( String username ) {
        Connection             connection = ConnectionFactory.getConnection ( );
        ArrayList < Contrato > contratos  = new ArrayList<>( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_ALL_POR_PROFESSOR );
            ps.setString ( 1 , username );

            ResultSet rs = ps.executeQuery ( );


            while (rs.next ( )) {
                String usernameAluno  = rs.getNString ( columnUsernameAluno );
                String codAula        = rs.getNString ( columnCodAula );
                String codContrato    = rs.getString ( columnCodContrato );
                Date   dataFinal      = rs.getDate ( columnDataFinal );
                Date   dataInicio     = rs.getDate ( columnDataInicio );
                int    statusContrato = rs.getInt ( columnStatusContrato );

                Usuario usuario = new UsuarioDAOMySQL ( ).getUser ( usernameAluno );
                Aluno aluno = new Aluno ( usuario.getUsername ( ) , usuario.getNome ( ) , usuario.getFormacao ( ) , usuario.getDataNascimento ( ) ,
                        usuario.getDescricao ( )  );

                Aula aula = new AulaDAOMySQL ( ).getAula ( codAula );

                Contrato contrato = new Contrato ( codContrato , aluno , aula , dataInicio , dataFinal , ContratoEtapa.identificarPorId ( statusContrato ) );
                contratos.add ( contrato );
            }

            return contratos;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public List < Contrato > getAllContratosByAluno ( String username ) {
        Connection             connection = ConnectionFactory.getConnection ( );
        ArrayList < Contrato > contratos  = new ArrayList <> ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_ALL_POR_ALUNO );
            ps.setString ( 1 , username );

            ResultSet rs = ps.executeQuery ( );
            while (rs.next ( )) {
                String usernameAluno  = rs.getNString ( columnUsernameAluno );
                String codAula        = rs.getNString ( columnCodAula );
                String codContrato    = rs.getString ( columnCodContrato );
                Date   dataFinal      = rs.getDate ( columnDataFinal );
                Date   dataInicio     = rs.getDate ( columnDataInicio );
                int    statusContrato = rs.getInt ( columnStatusContrato );

                Usuario usuario = new UsuarioDAOMySQL ( ).getUser ( usernameAluno );
                Aluno aluno = new Aluno ( usuario.getUsername ( ) , usuario.getNome ( ) , usuario.getFormacao ( ) ,
                        usuario.getDataNascimento ( ) , usuario.getDescricao ( )  );
                Aula aula = new AulaDAOMySQL ( ).getAula ( codAula );

                Contrato contrato = new Contrato ( codContrato , aluno , aula , dataInicio , dataFinal ,
                        ContratoEtapa.identificarPorId ( statusContrato ) );
                contratos.add ( contrato );

            }

            return contratos;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public List < Contrato > getAllContratosByAlunoAndEtapa ( String username , ContratoEtapa etapa ) {
        Connection             connection = ConnectionFactory.getConnection ( );
        ArrayList < Contrato > contratos  = new ArrayList <> ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_ALL_POR_ALUNO_AND_ETAPA );
            ps.setString ( 1 , username );
            ps.setInt ( 2 , etapa.getId ( ) );

            ResultSet rs = ps.executeQuery ( );


            while (rs.next ( )) {
                String usernameAluno  = rs.getNString ( columnUsernameAluno );
                String codAula        = rs.getNString ( columnCodAula );
                String codContrato    = rs.getString ( columnCodContrato );
                Date   dataFinal      = rs.getDate ( columnDataFinal );
                Date   dataInicio     = rs.getDate ( columnDataInicio );
                int    statusContrato = rs.getInt ( columnStatusContrato );

                Usuario usuario = new UsuarioDAOMySQL ( ).getUser ( usernameAluno );
                Aluno aluno = new Aluno ( usuario.getUsername ( ) ,
                        usuario.getNome ( ) ,
                        usuario.getFormacao ( ) ,
                        usuario.getDataNascimento ( ) ,
                        usuario.getDescricao ( ) );

                Aula aula = new AulaDAOMySQL( ).getAula ( codAula );

                Contrato contrato = new Contrato (codContrato,
                        aluno ,
                        aula ,
                        dataInicio ,
                        dataFinal ,
                        ContratoEtapa.identificarPorId ( statusContrato ) );

                contratos.add(contrato);
            }

            return contratos;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

    @Override
    public List < Contrato > getAllContratosByProfessorAndEtapa ( String username , ContratoEtapa etapa ) {
        Connection             connection = ConnectionFactory.getConnection ( );
        ArrayList < Contrato > contratos  = new ArrayList <> ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_ALL_POR_PROFESSOR_AND_ETAPA );
            ps.setString ( 1 , username );
            ps.setInt ( 2 , etapa.getId ( ) );

            ResultSet rs = ps.executeQuery ( );


            while (rs.next ( )) {
                String usernameAluno  = rs.getNString ( columnUsernameAluno );
                String codAula        = rs.getNString ( columnCodAula );
                String codContrato    = rs.getString ( columnCodContrato );
                Date   dataFinal      = rs.getDate ( columnDataFinal );
                Date   dataInicio     = rs.getDate ( columnDataInicio );
                int    statusContrato = rs.getInt ( columnStatusContrato );

                Usuario usuario = new UsuarioDAOMySQL ( ).getUser ( usernameAluno );
                Aluno aluno = new Aluno ( usuario.getUsername ( ) , usuario.getNome ( ) , usuario.getFormacao ( ) , usuario.getDataNascimento ( ) ,
                        usuario.getDescricao ( )  );

                Aula aula = new AulaDAOMySQL ( ).getAula ( codAula );

                Contrato contrato = new Contrato (codContrato, aluno , aula , dataInicio , dataFinal , ContratoEtapa.identificarPorId ( statusContrato ) );
                contratos.add(contrato);

            }

            return contratos;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }

        return null;
    }


}
