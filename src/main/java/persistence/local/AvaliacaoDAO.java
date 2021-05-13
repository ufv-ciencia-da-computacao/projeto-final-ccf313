package persistence.local;

import model.Avaliacao;
import persistence.interfaces.IAvaliacaoDAO;

import java.util.List;

public class AvaliacaoDAO implements IAvaliacaoDAO {
    private LocalDatabaseSingleton db;

    public AvaliacaoDAO() {
        this.db = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public void addAvaliacao(Avaliacao avaliacao) {
        List<Avaliacao> avaliacoes = this.db.avaliacoes.get(avaliacao.getAvaliado().getUsername());
        avaliacoes.add(avaliacao);
        this.db.avaliacoes.put(avaliacao.getAvaliado().getUsername(), avaliacoes);
    }

    @Override
    public List<Avaliacao> getByUsuario(String username) {
        return this.db.avaliacoes.get(username);
    }
}
