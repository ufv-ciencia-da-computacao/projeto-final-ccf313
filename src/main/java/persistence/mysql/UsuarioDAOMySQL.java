package persistence.mysql;

import model.Usuario;
import persistence.interfaces.IUsuarioDAO;

import java.sql.*;
import java.sql.Date;

import java.util.*;

public class UsuarioDAOMySQL implements IUsuarioDAO {
    final String columnNome="nome";
    final String columnDescricao="descricao";
    final String columndataNascimento="dataNascimento";
    final String columnFormacao="formacao";
    final String columnUsername="username";
    final String columnTipoUsuario="tipoUsuario";

    final String INSERT_USER="INSERT INTO usuario("+columnNome+","+columnDescricao+","+
        columndataNascimento+","+columnFormacao+","+columnUsername+","+columnTipoUsuario+") VALUES (?,?,?,?,?,?);";
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
            ps.setInt(6, usuario.getTipoUsuario());

            ps.executeUpdate();
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
                String formacao= rs.getNString(columnFormacao);
                Date dataNascimento= rs.getDate(columndataNascimento);
                String descricao= rs.getNString(columnDescricao);
                String userName= rs.getNString(columnUsername);
                int tipoUsuario= rs.getInt(columnTipoUsuario);


                return new Usuario(userName,nome,formacao,
                        dataNascimento,descricao,tipoUsuario);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Usuario> getAllUser() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Usuario> usuarios=new ArrayList<>();
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
                int tipoUsuario= rs.getInt(columnTipoUsuario);

                Usuario user= new Usuario(userName,nome,formacao,
                        dataNascimento,descricao,tipoUsuario);

                usuarios.add(user);
            }

            return usuarios;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }



}
