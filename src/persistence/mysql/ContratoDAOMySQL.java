package persistence.mysql;

import model.Aluno;
import model.Contrato;
import model.ContratoEtapa;
import model.Usuario;
import persistence.interfaces.IContratoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ContratoDAOMySQL implements IContratoDAO {
    final String columnUsernameAluno="usernameAluno";
    final String columnCodAula="codAula";
    final String columnCodContrato="codContrato";
    final String columnDataFinal="dataFinal";
    final String columnDataInicio="dataInicial";
    final String getColumnStatusContrato="statusContrato";


    final String INSERT_CONTRATO_AULA="INSERT INTO contratoaula("+columnUsernameAluno+","+columnCodAula+","+
            columnCodContrato+","+columnDataFinal+","+columnDataInicio+","+getColumnStatusContrato+") VALUES (?,?,?,?,?,?);";

    final String SELECT_POR_DISCIPLINA="SELECT * FROM aula WHERE codDisciplina=?;";
    final String SELECT_POR_COD_AULA="SELECT * FROM aula WHERE codAula=?;";
    final String SELECT_ALL_AULAS="SELECT * FROM aula ;";
    final String SELECT_TOPICOS_POR_COD_AULA="SELECT * FROM topicoaula WHERE codAula=?;";

    final String UPDATE_AULA=("UPDATE aula SET valorHora=?, descricao=?, codDisciplina=?, usernameProfessor=? WHERE codAula=?");

    final String DELETE_AULA="DELETE FROM aula WHERE codAula=?" ;
    final String DELETE_TOPICO_AULA="DELETE FROM topicoaula WHERE codAula=?" ;
    @Override
    public void submitContrato(Contrato contrato) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_CONTRATO_AULA);

            ps.setString(1,contrato.getAluno().getUsername());
            ps.setString(2,contrato.getAula().getCodAula());
            ps.setString(3,contrato.getCodContrato() );
            ps.setDate(4, (Date) contrato.getDataFinal());
            ps.setDate(5, (Date) contrato.getDataComeco());
            ps.setInt(6,contrato.getEtapas().getId() );
            int i=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public void acceptContrato(String codContrato) {

    }

    @Override
    public void declineContrato(String codContrato) {

    }

    @Override
    public List<Contrato> getAllContratosByProfessor(String username) {
        return null;
    }

    @Override
    public List<Contrato> getAllContratosByAluno(String username) {
        return null;
    }

    @Override
    public List<Contrato> getAllContratosByAlunoAndEtapa(String username, ContratoEtapa etapa) {
        return null;
    }

    @Override
    public List<Contrato> getAllContratosByProfessorAndEtapa(String username, ContratoEtapa etapa) {
        return null;
    }

    public static void main(String[] args){
        ContratoDAOMySQL contratoDAOMySQL=new ContratoDAOMySQL();
        Usuario usuario= new UsuarioDAOMySQL().getUser("dener");
        Aluno aluno= new Aluno(usuario.getUsername(), usuario.getNome(), usuario.getFormacao(), usuario.getDataNascimento(),
                usuario.getDescricao());
       // Contrato contrato= new Contrato(aluno,"",);
    }
}
