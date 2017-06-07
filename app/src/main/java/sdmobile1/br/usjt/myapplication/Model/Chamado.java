package sdmobile1.br.usjt.myapplication.Model;

import java.io.Serializable;

/**
 * Created by Diego on 22/05/2017.
 */

public class Chamado implements Serializable {
    private Integer idChamado;
    private String nmChamado;
    private String mensagem;
    private String resposta;
    private String status;
    private String dtInclusao;
    private String dtAlteracao;
    private String dtExclusao;
    private Fila fila;

    public String getNmChamado() {
        return nmChamado;
    }

    public void setNmChamado(String nmChamado) {
        this.nmChamado = nmChamado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
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

    public String getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(String dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public String getDtExclusao() {
        return dtExclusao;
    }

    public void setDtExclusao(String dtExclusao) {
        this.dtExclusao = dtExclusao;
    }

    public Fila getFila() {
        return fila;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
    }
}

