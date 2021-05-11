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
    final String columnUsernameAvaliador="usernameAvaliador";
    final String columnUsernameProfessor="usernameProfessor";
    final String columnComentario="comentario";
    final String columnValor="valor";


    final String INSERT_AVALIACAO="INSERT INTO avaliacaousuario("+columnUsernameAvaliador+","+columnUsernameProfessor+","+
            columnComentario+","+columnValor+") VALUES (?,?,?,?);";
    final String SELECT_AVALIACOES="SELECT * FROM avaliacaousuario WHERE usernameProfessor=?;";

    @Override
    public void addAvaliacao(Avaliacao avaliacao) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_AVALIACAO);

            ps.setString(1,avaliacao.getAvaliador().getUsername());
            ps.setString(2, avaliacao.getAvaliado().getUsername());
            ps.setString(3, avaliacao.getComentario());
            ps.setDouble(4, avaliacao.getValor());
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Avaliacao> getByUsuario(String username) {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Avaliacao> avaliacoes=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_AVALIACOES);
            ps.setObject(1,username);
            ResultSet rs= ps.executeQuery();
            UsuarioDAOMySQL usuarioDAOMySQL=new UsuarioDAOMySQL();


            while(rs.next())
            {
                String usernameAvaliador= rs.getNString(columnUsernameAvaliador);
                String usernameProfessor= rs.getNString(columnUsernameProfessor);
                String comentario= rs.getNString(columnComentario);
                double valor= rs.getDouble(columnValor);

                Usuario userAvaliador= usuarioDAOMySQL.getUser(usernameAvaliador);
                Usuario userAvaliado= usuarioDAOMySQL.getUser(usernameProfessor);
                Avaliacao avaliacao = new Avaliacao(userAvaliador,userAvaliado,valor,comentario);
                avaliacoes.add(avaliacao);
                System.out.println(avaliacao.getAvaliador().getUsername());
                System.out.println(avaliacao.getAvaliado().getUsername());
                System.out.println(avaliacao.getComentario());
                System.out.println(avaliacao.getValor());
            }

            return avaliacoes;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }
    public static void main(String[] args) {
        //AvaliacaoDAOMySQL avaliacaoDAOMySQL=new AvaliacaoDAOMySQL();
//        Usuario usuario=new Usuario("dener","Dener","talarico",
//           new Date(2001-1900,2,12),"raspa canela");

//        Usuario usuario3=new Usuario("Fabio","fabio","lindeza",
//                new Date(2001-1900,8,5),"top d+");

//        Usuario usuario2=new Usuario("gegen07","Germano","vagabundo",
//                    new Date(2001-1900,2,25),"descricao");

        //Avaliacao avaliacao=new Avaliacao(usuario3,usuario2,0.1,"comentario");
        //avaliacaoDAOMySQL.addAvaliacao(avaliacao);
        //avaliacaoDAOMySQL.getByUsuario("gegen07");

    }
}
