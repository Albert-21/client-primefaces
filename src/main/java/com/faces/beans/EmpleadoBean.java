/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.beans;

import com.faces.entity.Empleado;
import com.faces.factory.FactoryMethod;
import com.faces.factory.IDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author victorm
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private boolean res;
    private Empleado empleado;
    private IDAO daoEmp;

    /**
     * Creates a new instance of EmpleadoBean
     */
    public EmpleadoBean() {
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void guardar() {
        res = false;
        daoEmp = FactoryMethod.create(FactoryMethod.TypeDAO.EMPLEADO);
        res = daoEmp.insert(getEmpleado());
        if (res) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El empleado se ha guardado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
        }

    }

    public void actualizar() {
        res = false;
        daoEmp = FactoryMethod.create(FactoryMethod.TypeDAO.EMPLEADO);
        res = daoEmp.update(getEmpleado());
        if (res) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El empleado se ha actualizado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
        }

    }

    public void eliminar() {
        res = false;
        try {
            daoEmp = FactoryMethod.create(FactoryMethod.TypeDAO.EMPLEADO);
            daoEmp.delete(empleado.getId());
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El empleado se ha eliminado exitosamente");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error");
            System.out.println("error " + e.getMessage());
        }
    }
}
