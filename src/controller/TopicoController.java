package controller;

import exceptions.TopicoExistente;
import exceptions.TopicoNaoEncontrado;
import model.Topico;
import persistence.interfaces.ITopicoDAO;
import persistence.interfaces.IUsuarioDAO;

import java.util.List;

public class TopicoController {
    IUsuarioDAO usuarioDAO;
    ITopicoDAO topicoDAO;

    public TopicoController(IUsuarioDAO usuarioDAO, ITopicoDAO topicoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.topicoDAO = topicoDAO;
    }

    public void addTopico(Topico topico) throws TopicoExistente {
        List<Topico> topicos = topicoDAO.getAllTopicos();

        for (Topico t:topicos) {
            if (t.getDescricao().toLowerCase().equals(topico.getDescricao().toLowerCase()))
                throw new TopicoExistente("Topico já existe!");
        }

        topicoDAO.addTopico(topico);
    }

    public List<Topico> getAllTopicos() {
        List<Topico> topicos = topicoDAO.getAllTopicos();
        return topicos;
    }

    public Topico getTopico(String descricao) throws TopicoNaoEncontrado {
        List<Topico> topicos = topicoDAO.getAllTopicos();
        Topico top = null;
        for (Topico t:topicos) {
            if (t.getDescricao().toLowerCase().equals(descricao.toLowerCase()))
                throw new TopicoNaoEncontrado("Topico não encontrado!");
            else {
                top = topicoDAO.getTopico(t.getId());
                break;
            }
        }
        return top;
    }
}
