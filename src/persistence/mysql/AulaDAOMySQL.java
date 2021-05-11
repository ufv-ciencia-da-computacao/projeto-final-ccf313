package persistence.mysql;

import model.*;
import persistence.interfaces.IAulaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDAOMySQL implements IAulaDAO {
    final String columnCodAula="codAula";
    final String columnvalorHora="valorHora";
    final String columnDescricao="descricao";
    final String columnCodDisciplina="codDisciplina";
    final String columnUsernameProfessor="usernameProfessor";

    final String columnIdTopico="idTopico";


    final String INSERT_AULA="INSERT INTO aula("+columnCodAula+","+columnvalorHora+","+
            columnDescricao+","+columnCodDisciplina+","+columnUsernameProfessor+") VALUES (?,?,?,?,?);";
    final String INSERT_TOPICO_AULA="INSERT INTO topicoaula("+columnIdTopico+","+columnCodAula+") VALUES (?,?);";

    final String SELECT_POR_DISCIPLINA="SELECT * FROM aula WHERE codDisciplina=?;";
    final String SELECT_POR_COD_AULA="SELECT * FROM aula WHERE codAula=?;";
    final String SELECT_ALL_AULAS="SELECT * FROM aula ;";
    final String SELECT_TOPICOS_POR_COD_AULA="SELECT * FROM topicoaula WHERE codAula=?;";

    final String UPDATE_AULA=("UPDATE aula SET valorHora=?, descricao=?, codDisciplina=?, usernameProfessor=? WHERE codAula=?");

    final String DELETE_AULA="DELETE FROM aula WHERE codAula=?" ;
    final String DELETE_TOPICO_AULA="DELETE FROM topicoaula WHERE codAula=?" ;

    public void addTopicoAula(int idTopico,String codAula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TOPICO_AULA);

            ps.setInt(1,idTopico);
            ps.setString(2, codAula);

            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public void addAula(Aula aula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_AULA);

            ps.setString(1,aula.getCodAula());
            ps.setDouble(2, aula.getValorHora());
            ps.setString(3, aula.getDescricao());
            ps.setString(4, aula.getDisciplina().getCodDisciplina());
            ps.setString(5, aula.getProfessor().getUsername());

            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        for (int i=0;i<aula.getTopicos().size();i++){
            addTopicoAula(aula.getTopicos().get(i).getId(),aula.getCodAula());
        }

    }

    @Override
    public  void updateAula(Aula aula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_AULA);
            ps.setDouble(1, aula.getValorHora());
            ps.setString(2, aula.getDescricao());
            ps.setString(3, aula.getDisciplina().getCodDisciplina());
            ps.setString(4, aula.getProfessor().getUsername());
            ps.setString(5,aula.getCodAula());
            int i = ps.executeUpdate();
            //    System.out.println(aula.getProfessor().getUsername());
//                System.out.println(aula.getCodAula());
//                System.out.println(aula.getDisciplina().getCodDisciplina());
//                System.out.println(aula.getDescricao());
//                System.out.println(aula.getValorHora());
//                System.out.println(aula.getTopicos().size());


        } catch (SQLException ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public Aula getAula(String codAula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_POR_COD_AULA);
            ps.setObject(1,codAula);
            ResultSet rs= ps.executeQuery();
            UsuarioDAOMySQL usuarioDAOMySQL=new UsuarioDAOMySQL();
            DisciplinaDAOMySQL disciplinaDAOMySQL= new DisciplinaDAOMySQL();


            if(rs.next())
            {

                String usernameProfessor= rs.getNString(columnUsernameProfessor);
                double valorHora= rs.getDouble(columnvalorHora);
                String descricao= rs.getNString(columnDescricao);
                String codDisciplina= rs.getNString(columnCodDisciplina);

                Usuario userProfessor= usuarioDAOMySQL.getUser(usernameProfessor);
                Disciplina disciplina= disciplinaDAOMySQL.getDisciplina(codDisciplina);

                Professor professor=new Professor(userProfessor.getUsername(), userProfessor.getNome(), userProfessor.getFormacao(),
                        userProfessor.getDataNascimento(), userProfessor.getDescricao(),userProfessor.getTipoUsuario());

                ArrayList<Topico> topicos = getTopicosDaAula(codAula);
                Aula aula = new Aula(topicos,professor,valorHora,disciplina,descricao);
                aula.setCodAula(codAula);


                return  aula;


            }



        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Aula> getAulaByDisciplina(String codDisciplina) {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Aula> aulas=new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_POR_DISCIPLINA);
            ps.setObject(1,codDisciplina);
            ResultSet rs= ps.executeQuery();
            UsuarioDAOMySQL usuarioDAOMySQL=new UsuarioDAOMySQL();
            DisciplinaDAOMySQL disciplinaDAOMySQL= new DisciplinaDAOMySQL();


            while(rs.next())
            {

                String usernameProfessor= rs.getNString(columnUsernameProfessor);
                double valorHora= rs.getDouble(columnvalorHora);
                String descricao= rs.getNString(columnDescricao);
                String codAula= rs.getNString(columnCodAula);

                Usuario userProfessor= usuarioDAOMySQL.getUser(usernameProfessor);
                Disciplina disciplina= disciplinaDAOMySQL.getDisciplina(codDisciplina);


                Professor professor=new Professor(userProfessor.getUsername(), userProfessor.getNome(), userProfessor.getFormacao(),
                        userProfessor.getDataNascimento(), userProfessor.getDescricao(),userProfessor.getTipoUsuario());

                ArrayList<Topico> topicos = getTopicosDaAula(codAula);
                Aula aula = new Aula(topicos,professor,valorHora,disciplina,descricao);
                aula.setCodAula(codAula);
                aulas.add(aula);

            }
            return aulas;


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Aula> getAllAulas() {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Aula> aulas=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_AULAS);

            ResultSet rs= ps.executeQuery();


            while(rs.next())
            {
                String codAula= rs.getNString(columnCodAula);
                Aula aula= getAula(codAula);



                aulas.add(aula);
               System.out.println(aulas.size());

            }

            return aulas;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public void deleteAula(String codAula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            deleteTopicoAula(codAula);
            PreparedStatement ps = connection.prepareStatement(DELETE_AULA);
            ps.setString(1, codAula);

            int i = ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public ArrayList<Topico> getTopicosDaAula(String codAula){

        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Topico> topicos=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_TOPICOS_POR_COD_AULA);
            ps.setObject(1,codAula);
            ResultSet rs= ps.executeQuery();


            while(rs.next())
            {
                int idTopico= rs.getInt("idTopico");
                Topico topico= new TopicoDAOMySQL().getTopico(idTopico);

                topicos.add(topico);

               // System.out.println(topico.getDescricao());
            }

            return topicos;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    public void deleteTopicoAula(String codAula) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_TOPICO_AULA);
            ps.setString(1, codAula);

            int i = ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
    public static void main(String[] args){
       AulaDAOMySQL aulaDAOMySQL=new AulaDAOMySQL();

        Topico topico1=new TopicoDAOMySQL().getTopico(1);
        ArrayList<Topico> topicos=new ArrayList<>();
        topicos.add(topico1);

        Usuario usuario=  new UsuarioDAOMySQL().getUser("fabio");

        Professor professor=new Professor(usuario.getUsername(), usuario.getNome(), usuario.getFormacao(),
                usuario.getDataNascimento(),usuario.getDescricao(), usuario.getTipoUsuario());

        Disciplina disciplina=new DisciplinaDAOMySQL().getDisciplina("CFC");
        disciplina.setCodDisciplina("MAF");

        Aula aula=new Aula(topicos,professor,10.5,disciplina,"descricao");
        aulaDAOMySQL.addAula(aula);

        //aulaDAOMySQL.getTopicosDaAula("8d1419b3-44c0-438c-868c-2862b798e134");
        //aulaDAOMySQL.getAula("8d1419b3-44c0-438c-868c-2862b798e134");
        //aulaDAOMySQL.getAllAulas();

        //aulaDAOMySQL.getAulaByDisciplina("MAF");
        //Aula aula= aulaDAOMySQL.getAula("e64d0742-4218-4570-a582-e5bcd15db347");

        //aula.setValorHora(30.0);
       // aula.setDescricao("nova descricao2");
        //aulaDAOMySQL.updateAula(aula);

        //aulaDAOMySQL.deleteAula("4f078cbe-1b75-416c-bf49-af0e59f27490");
    }
}
