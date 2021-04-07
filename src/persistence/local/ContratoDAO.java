package persistence.local;

import model.Contrato;
import model.ContratoEtapa;
import persistence.interfaces.IContratoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContratoDAO implements IContratoDAO {
    LocalDatabaseSingleton localDatabase;

    public ContratoDAO(LocalDatabaseSingleton localDatabase) {
        this.localDatabase = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public void submitContrato(Contrato contrato) {
        localDatabase.contratos.put(contrato.getCodContrato(), contrato);
    }

    @Override
    public void acceptContrato(String codContrato) {
        Contrato c = localDatabase.contratos.get(codContrato);
        c.setEtapas(ContratoEtapa.ACEITO);
        localDatabase.contratos.put(c.getCodContrato(), c);
    }

    @Override
    public void declineContrato(String codContrato) {
        Contrato c = localDatabase.contratos.get(codContrato);
        c.setEtapas(ContratoEtapa.DECLINADO);
        localDatabase.contratos.put(c.getCodContrato(), c);
    }

    @Override
    public List<Contrato> getAllContratosByProfessor(String email) {
        List<Contrato> contratos = new ArrayList<Contrato>();
        for (Map.Entry<String, Contrato> entry: localDatabase.contratos.entrySet()) {
            Contrato c = entry.getValue();
            if (c.getAula().getProfessor().getEmail().equals(email)) {
                contratos.add(c);
            }
        }
        return contratos;
    }

    @Override
    public List<Contrato> getAllContratosByAluno(String email) {
        List<Contrato> contratos = new ArrayList<Contrato>();
        for (Map.Entry<String, Contrato> entry: localDatabase.contratos.entrySet()) {
            Contrato c = entry.getValue();
            if (c.getAluno().getEmail().equals(email)) {
                contratos.add(c);
            }
        }
        return contratos;
    }

    @Override
    public List<Contrato> getAllContratosByAlunoAndEtapa(String email, ContratoEtapa etapa) {
        List<Contrato> contratos = new ArrayList<Contrato>();
        for (Map.Entry<String, Contrato> entry: localDatabase.contratos.entrySet()) {
            Contrato c = entry.getValue();
            if (c.getAluno().getEmail().equals(email) && c.getEtapas() == etapa) {
                contratos.add(c);
            }
        }
        return contratos;
    }

    @Override
    public List<Contrato> getAllContratosByProfessorAndEtapa(String email, ContratoEtapa etapa) {
        List<Contrato> contratos = new ArrayList<Contrato>();
        for (Map.Entry<String, Contrato> entry: localDatabase.contratos.entrySet()) {
            Contrato c = entry.getValue();
            if (c.getAula().getProfessor().getEmail().equals(email) && c.getEtapas()==etapa) {
                contratos.add(c);
            }
        }
        return contratos;
    }
}