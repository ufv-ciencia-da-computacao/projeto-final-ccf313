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
    public Contrato(Aluno aluno, Aula aula, Date dataComeco, Date dataFinal,ContratoEtapa contratoEtapa) {
        this(aluno, aula, dataComeco);
        this.dataFinal = dataFinal;
        this.etapas=contratoEtapa;
    }
    public Contrato(String codContrato,Aluno aluno, Aula aula, Date dataComeco, Date dataFinal,ContratoEtapa contratoEtapa) {
        this(aluno, aula, dataComeco,dataFinal,contratoEtapa);
       this.codContrato=codContrato;
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

    public Date getDataComeco() {
        return dataComeco;
    }

    public void setDataComeco(Date dataComeco) {
        this.dataComeco = dataComeco;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    public void setCodContrato(String codContrato) {
        this.codContrato = codContrato;
    }
}
