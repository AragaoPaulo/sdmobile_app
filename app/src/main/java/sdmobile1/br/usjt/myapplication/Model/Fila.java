package sdmobile1.br.usjt.myapplication.Model;

import java.io.Serializable;

/**
 * Created by Diego on 23/05/2017.
 */

public class Fila implements Serializable {

    private Integer idFila;
    private Integer idChamado;
    private Integer idSolicitante;
    private Integer idSolucionador;
    private Integer prioridade;
    private String status;
    private String dtInclusao;
    private String dtExclusao;

    public Integer getIdFila() {
        return idFila;
    }

    public void setIdFila(Integer idFila) {
        this.idFila = idFila;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Integer getIdSolucionador() {
        return idSolucionador;
    }

    public void setIdSolucionador(Integer idSolucionador) {
        this.idSolucionador = idSolucionador;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getDtExclusao() {
        return dtExclusao;
    }

    public void setDtExclusao(String dtExclusao) {
        this.dtExclusao = dtExclusao;
    }
}
