package persistence.mysql;

import model.Avaliacao;
import model.Usuario;
import persistence.interfaces.IAvaliacaoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AvaliacaoDAOMySQL implements IAvaliacaoDAO {
    final String columnUsernameAvaliador = "usernameAvaliador";
    final String columnUsernameProfessor = "usernameProfessor";
    final String columnComentario = "comentario";
    final String columnValor = "valor";


    final String INSERT_AVALIACAO = "INSERT INTO avaliacaousuario(" + columnUsernameAvaliador + "," + columnUsernameProfessor + "," +
            columnComentario + "," + columnValor + ") VALUES (?,?,?,?);";
    final String SELECT_AVALIACOES = "SELECT * FROM avaliacaousuario WHERE usernameProfessor=?;";

    @Override
    public void addAvaliacao ( Avaliacao avaliacao ) {
        Connection connection = ConnectionFactory.getConnection ( );

        try {
            PreparedStatement ps = connection.prepareStatement ( INSERT_AVALIACAO );

            ps.setString ( 1 , avaliacao.getAvaliador ( ).getUsername ( ) );
            ps.setString ( 2 , avaliacao.getAvaliado ( ).getUsername ( ) );
            ps.setString ( 3 , avaliacao.getComentario ( ) );
            ps.setDouble ( 4 , avaliacao.getValor ( ) );
            int i = ps.executeUpdate ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    @Override
    public List < Avaliacao > getByUsuario ( String username ) {
        Connection connection = ConnectionFactory.getConnection ( );
        ArrayList < Avaliacao > avaliacoes = new ArrayList <> ( );
        try {
            PreparedStatement ps = connection.prepareStatement ( SELECT_AVALIACOES );
            ps.setObject ( 1 , username );
            ResultSet rs = ps.executeQuery ( );
            UsuarioDAOMySQL usuarioDAOMySQL = new UsuarioDAOMySQL ( );


            while (rs.next ( )) {
                String usernameAvaliador = rs.getNString ( columnUsernameAvaliador );
                String usernameProfessor = rs.getNString ( columnUsernameProfessor );
                String comentario = rs.getNString ( columnComentario );
                double valor = rs.getDouble ( columnValor );

                Usuario userAvaliador = usuarioDAOMySQL.getUser ( usernameAvaliador );
                Usuario userAvaliado = usuarioDAOMySQL.getUser ( usernameProfessor );
                Avaliacao avaliacao = new Avaliacao ( userAvaliador , userAvaliado , valor , comentario );
                avaliacoes.add ( avaliacao );

            }

            return avaliacoes;

        } catch ( SQLException ex ) {
            ex.printStackTrace ( );
        }


        return null;
    }

}
