package persistence.mysql;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import java.sql.*;
import java.sql.Date;

import java.util.*;

public class UsuarioDAOMySQL implements IUsuarioDAO {
    final String columnNome="nome";
    final String columnIdUsuario="idUsuario";
    final String columnDescricao="descricao";
    final String columndataNascimento="dataNascimento";
    final String columnFormacao="formacao";
    final String columnUsername="username";

    final String INSERT_USER="INSERT INTO usuario("+columnNome+","+columnDescricao+","+
        columndataNascimento+","+columnFormacao+","+columnUsername+") VALUES (?,?,?,?,?);";
    final String SELECT_USER="SELECT * FROM usuario WHERE username=?;";
    final String SELECT_ALL_USERS="SELECT * FROM usuario;";






    @Override
    public void addUser(Usuario usuario) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);

            ps.setString(1,usuario.getNome());
            ps.setString(2, usuario.getDescricao());
            ps.setDate(3,  new java.sql.Date(usuario.getDataNascimento().getTime()));
            ps.setString(4, usuario.getFormacao());
            ps.setString(5, usuario.getUsername());
            int i=ps.executeUpdate();
        }catch (SQLException  e){
            e.printStackTrace();
        }

    }

    @Override
    public Usuario getUser(String username) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER);
             ps.setString(1,username);
             ResultSet rs= ps.executeQuery();

            if(rs.next())
            {
                String nome= rs.getNString(columnNome);
                int idUsuario= rs.getInt(columnIdUsuario);
                String formacao= rs.getNString(columnFormacao);
                Date dataNascimento= rs.getDate(columndataNascimento);
                String descricao= rs.getNString(columnDescricao);
                String userName= rs.getNString(columnUsername);

//                System.out.println(userName);
////                System.out.println(descricao);
////                System.out.println(formacao);
////                System.out.println(nome);
////                System.out.println(dataNascimento);


                return new Usuario(userName,nome,formacao,
                        dataNascimento,descricao);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public ArrayList<Usuario> getAllUser() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet rs= ps.executeQuery();


            while(rs.next())
            {
                String nome= rs.getNString(columnNome);
                String formacao= rs.getNString(columnFormacao);
                Date dataNascimento= rs.getDate(columndataNascimento);
                String descricao= rs.getNString(columnDescricao);
                String userName= rs.getNString(columnUsername);

                Usuario user= new Usuario(userName,nome,formacao,
                        dataNascimento,descricao);

                usuarios.add(user);
//                System.out.println(userName);
//                System.out.println(descricao);
//                System.out.println(formacao);
//                System.out.println(nome);
//                System.out.println(dataNascimento);
            }

            return usuarios;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args) {
        UsuarioDAOMySQL usuarioDAOMySQL=new UsuarioDAOMySQL();
        //Usuario usuario=new Usuario("dener","Dener","talarico",
          //   new Date(2001-1900,2,12),"raspa canela");
        //usuarioDAOMySQL.addUser(usuario);
       // usuarioDAOMySQL.getUser("gegen07");
        usuarioDAOMySQL.getAllUser();

    }
}
