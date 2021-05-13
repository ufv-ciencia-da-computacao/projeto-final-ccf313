package persistence.interfaces;

import model.Contrato;
import model.ContratoEtapa;

import java.util.List;

public interface IContratoDAO {
    void submitContrato(Contrato contrato);
    void acceptContrato(String codContrato);
    void declineContrato(String codContrato);
    List<Contrato> getAllContratosByProfessor(String username);
    List<Contrato> getAllContratosByAluno(String username);
    List<Contrato> getAllContratosByAlunoAndEtapa(String username, ContratoEtapa etapa);
    List<Contrato> getAllContratosByProfessorAndEtapa(String username, ContratoEtapa etapa);
}
