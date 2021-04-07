package persistence.interfaces;

import model.Contrato;
import model.ContratoEtapa;

import java.util.List;

public interface IContratoDAO {
    void submitContrato(Contrato contrato);
    void acceptContrato(String codContrato);
    void declineContrato(String codContrato);
    List<Contrato> getAllContratosByProfessor(String email);
    List<Contrato> getAllContratosByAluno(String email);
    List<Contrato> getAllContratosByAlunoAndEtapa(String email, ContratoEtapa etapa);
    List<Contrato> getAllContratosByProfessorAndEtapa(String email, ContratoEtapa etapa);
}
