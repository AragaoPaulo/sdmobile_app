package sdmobile1.br.usjt.myapplication.Model;

import java.io.Serializable;

/**
 * Created by Diego on 22/05/2017.
 */

public class Usuario implements Serializable {
    private Integer idUsuario;
    private String nmUsuario;
    private String cargo;
    private String perfil;
    private String email;
    private String senha;
    private String telefone;
    private char status;
    private String dtInclusao;
    private String dsPerfil;
    private String local;


    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getDsPerfil() {
        return dsPerfil;
    }

    public void setDsPerfil(String dsPerfil) {
        this.dsPerfil = dsPerfil;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
