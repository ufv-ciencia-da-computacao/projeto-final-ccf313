package persistence.mysql;

import model.Topico;
import model.Usuario;
import persistence.interfaces.ITopicoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicoDAOMySQL implements ITopicoDAO {
    final String columnIdTopico="idTopico";
    final String columnDescricao="descricao";


    final String INSERT_TOPICO="INSERT INTO topico("+columnDescricao+") VALUES (?);";
    final String SELECT_TOPICO="SELECT * FROM topico WHERE +idTopico=?;";
    final String SELECT_ALL_TOPICOS="SELECT * FROM topico;";
    @Override
    public void addTopico(Topico topico) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TOPICO);

            ps.setString(1,topico.getDescricao());

            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Topico> getAllTopicos() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Topico> topicos=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_TOPICOS);

            ResultSet rs= ps.executeQuery();


            while(rs.next())
            {
                int idTopico=rs.getInt(columnIdTopico);
                String descricao= rs.getNString(columnDescricao);


                Topico topico= new Topico(descricao);
                topico.setId(idTopico);
                topicos.add(topico);

                //System.out.println(descricao);

            }

            return topicos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public Topico getTopico(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_TOPICO);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();

            if(rs.next())
            {
                String descricao= rs.getNString(columnDescricao);
                Topico topico=  new Topico(descricao);
                topico.setId(id);
                //System.out.println(descricao);
                return topico;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public void deleteTopico(int id) {

    }

    public static void main(String[] args){
        Topico topico1=new Topico("topico1");
        Topico topico2=new Topico("topico2");
        TopicoDAOMySQL topicoDAOMySQL=new TopicoDAOMySQL();

        topicoDAOMySQL.getTopico(2);
    }
}
