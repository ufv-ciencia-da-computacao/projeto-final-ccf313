package persistence.mysql;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UsuarioDAOMySQL implements IUsuarioDAO {
    final String INSERT_USER="INSERT INTO usuario(nome,descricao,dataNascimento,formacao,mediaAvaliacoes,email)" +
            " VALUES (?,?,?,?,?,?)";

    @Override
    public void addUser(Usuario usuario) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1,usuario.getNome());
            ps.setString(2,usuario.getDescricao());
            ps.setDate(3,  new java.sql.Date(usuario.getDataNascimento().getTime()));
            ps.setString(4, usuario.getFormacao());
            ps.setDouble(5, usuario.getAvaliacao());
            ps.setString(6, usuario.getEmail());
            int i=ps.executeUpdate();
        }catch (SQLException  e){
            e.printStackTrace();
        }

    }

    @Override
    public Usuario getUser(String username) {
        final String SELECTUSER="";
        return null;
    }

    @Override
    public ArrayList<Usuario> getAllUser() {
        return null;
    }

    public static void main(String[] args) {
       // UsuarioDAOMySQL usuarioDAOMySQL=new UsuarioDAOMySQL();
        //Usuario usuario = new Usuario("germanobs@ufv.br"
          //      ,"Germano Barcelos dos Santos",
           //     "Estudante",
            //    new Date(2001-1900, 2,25));
        //usuarioDAOMySQL.addUser(usuario);

    }
}
