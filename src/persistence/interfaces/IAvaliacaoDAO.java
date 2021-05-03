package persistence.interfaces;

import model.Avaliacao;
import model.Usuario;

import java.util.List;

public interface IAvaliacaoDAO {
    public void addAvaliacao(Avaliacao avaliacao);
    public List<Avaliacao> getByUsuario(String username);
}
