package org.ifgoiano.barbearia.model;

public class Admin {
    private Integer idAdmin;
    private String login;
    private String senha;

    public Admin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Admin() {
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
