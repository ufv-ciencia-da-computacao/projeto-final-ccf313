package persistence.local;

import model.Topico;
import persistence.interfaces.ITopicoDAO;

import java.util.ArrayList;
import java.util.List;

public class TopicoDAO implements ITopicoDAO {
    private final LocalDatabaseSingleton localInstance;

    public TopicoDAO() {
        this.localInstance = LocalDatabaseSingleton.getInstance();
    }

    @Override
    public void addTopico(Topico topico) {
        localInstance.topicos.put(topico.getId(), topico);
    }

    @Override
    public List<Topico> getAllTopicos() {
        return new ArrayList<Topico>(localInstance.topicos.values());
    }

    @Override
    public Topico getTopico(int id) {
        return localInstance.topicos.get(id);
    }

    @Override
    public void deleteTopico(int id) {
        localInstance.topicos.remove(id);
    }
}
