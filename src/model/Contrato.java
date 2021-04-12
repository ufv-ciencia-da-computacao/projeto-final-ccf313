package model;

import java.util.Date;
import java.util.UUID;

public class Contrato {
    private String codContrato;
    private Aluno aluno;
    private Aula aula;
    private Date dataComeco;
    private Date dataFinal;
    private ContratoEtapa etapas;

    public Contrato(Aluno aluno, Aula aula, Date dataComeco) {
        codContrato = UUID.randomUUID().toString();
        this.aluno = aluno;
        this.aula = aula;
        this.dataComeco = dataComeco;
        etapas = ContratoEtapa.NEGOCIACAO;
    }

    public Contrato(Aluno aluno, Aula aula, Date dataComeco, Date dataFinal) {
        this(aluno, aula, dataComeco);
        this.dataFinal = dataFinal;
    }

    public String getCodContrato() {
        return codContrato;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public ContratoEtapa getEtapas() {
        return etapas;
    }

    public void setEtapas(ContratoEtapa etapa) {
        this.etapas = etapa;
    }
}
