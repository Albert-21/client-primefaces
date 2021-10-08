/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.beans;

import com.faces.daos.DepartamentoDAO;
import com.faces.factory.FactoryMethod;
import com.service.service.DepartamentoService;
import com.service.service.Departamento;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author alberto
 */
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private boolean res;
    private Departamento departamento;
    private DepartamentoDAO daoDep;
    private List<Departamento> despartammentos;
    private DepartamentoService departamentoService;

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new Departamento();
        daoDep = (DepartamentoDAO) FactoryMethod.create(FactoryMethod.TypeDAO.DEPARTAMENTO);
        //despartammentos = departamentoService.mostrarTodosDeprtamentos();

    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDespartammentos() {
        return despartammentos;
    }

    public void setDespartammentos(List<Departamento> despartammentos) {
        this.despartammentos = despartammentos;
    }
    
    

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    

    public void guardar() {
        res=false;
        res = departamentoService.guardarDepartamento(getDepartamento());
        if (res == true) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El departamento se ha guardado exitosamente");
            despartammentos = departamentoService.mostrarTodosDeprtamentos();
            PrimeFaces.current().ajax().update("form:card");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
        }

    }

    public void actualizar() {
        res=false;
        res = departamentoService.actualizarDepartamento(getDepartamento());
        if (res == true) {
            addMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "El departamento se ha actualizado exitosamente");
            despartammentos = departamentoService.mostrarTodosDeprtamentos();
            PrimeFaces.current().ajax().update("form:card");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
        }

    }

    public void eliminar() {
        res = false;
        res = daoDep.delete(departamento.getId());
        if (res) {
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "El departamento se ha eliminado exitosamente");
            despartammentos = departamentoService.mostrarTodosDeprtamentos();
            PrimeFaces.current().ajax().update("form:card");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
        }

    }
    
    public void info(){
        System.out.println("id "+departamento.getId()+" nombre "+departamento.getNombre());
    }
    
}
