/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.beans;

import com.faces.daos.UsuariosDAO;
import com.faces.entity.Usuarios;
import com.faces.factory.FactoryMethod;
import com.faces.factory.IDAO;
import com.faces.filter.SessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alberto
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    private Usuarios usuario;
    private UsuariosDAO daoUser;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        usuario= new Usuarios();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String login() {
        String str = null;
        daoUser = (UsuariosDAO) FactoryMethod.create(FactoryMethod.TypeDAO.USUARIO);
        if (!daoUser.iniciarSesion(usuario.getUsuario(),usuario.getContraseña() )) {
            addMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "El usuario y/o conraseña son incorrectos");
        } else {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("usuario", usuario.getUsuario());
            str = "/departamentos.xhtml?faces-redirect=true";
        }
        return str;
    }

    public void register() {
        daoUser = (UsuariosDAO) FactoryMethod.create(FactoryMethod.TypeDAO.USUARIO);
        if (daoUser.resgistrar(usuario.getUsuario(), usuario.getContraseña())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Se registro el usuario exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Ops", "El usuario ya esta registrado");

        }

    }

    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}
