package persistence.interfaces;

import model.Avaliacao;
import model.Usuario;

import java.util.List;

public interface IAvaliacaoDAO {
    void addAvaliacao(Avaliacao avaliacao);
    List<Avaliacao> getByUsuario(String username);
}
