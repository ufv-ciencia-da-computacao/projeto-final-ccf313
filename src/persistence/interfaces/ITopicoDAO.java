package persistence.interfaces;

import model.Topico;

import java.util.List;

public interface ITopicoDAO {
    void addTopico(Topico topico);
    List<Topico> getAllTopicos();
    Topico getTopico(int id);
}
