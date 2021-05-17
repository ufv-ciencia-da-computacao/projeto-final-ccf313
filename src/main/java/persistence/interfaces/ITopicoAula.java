package persistence.interfaces;

import model.Topico;

import java.util.ArrayList;

public interface ITopicoAula {
     ArrayList<Topico> getTopicosByAula(String codAula);
     void deleteTopicoAula(String codAula);
      void addTopicoAula(int idTopico,String codAula);
}
