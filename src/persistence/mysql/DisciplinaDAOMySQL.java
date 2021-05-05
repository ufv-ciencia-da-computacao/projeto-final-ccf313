package persistence.mysql;

import model.Disciplina;
import model.Usuario;
import persistence.interfaces.IDisciplinaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOMySQL implements IDisciplinaDAO {
    final String SELECT_DISCIPLINA="SELECT * FROM disciplina WHERE idDisciplina=?;";
    final String SELECT_ALL_USERS="SELECT * FROM disciplina;";

    final String  columnNome="nome";
    final String  columnDescricao="descricao";
    final String columnCodDisciplina="idDisciplina";

    @Override
    public List<Disciplina> getAllDisciplina() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Disciplina> disciplinas=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet rs= ps.executeQuery();


            while(rs.next())
            {
                String nome= rs.getNString(columnNome);

                String descricao= rs.getNString(columnDescricao);
                String codDisciplina= rs.getNString(columnCodDisciplina);

                Disciplina disciplina= new Disciplina(nome,descricao);

                disciplinas.add(disciplina);
                System.out.println(nome);
               System.out.println(descricao);
                System.out.println(codDisciplina);
            }

            return disciplinas;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Disciplina getDisciplina(String codDiscplina) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_DISCIPLINA);
            ps.setString(1,codDiscplina);
            ResultSet rs= ps.executeQuery();

            if(rs.next())
            {
                String nome= rs.getNString(columnNome);
                String idDisciplina= rs.getNString(columnCodDisciplina);
                String descricao = rs.getNString(columnDescricao);

                System.out.println(nome);
                System.out.println(idDisciplina);
                System.out.println(descricao);



                return new Disciplina(nome,descricao);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args) {
        DisciplinaDAOMySQL disciplinaDAOMySQL =new DisciplinaDAOMySQL();

        disciplinaDAOMySQL.getAllDisciplina();

    }
}
