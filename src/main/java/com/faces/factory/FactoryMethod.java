/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.factory;

import com.faces.daos.DepartamentoDAO;
import com.faces.daos.EmpleadoDAO;
import com.faces.daos.UsuariosDAO;



/**
 *
 * @author alberto
 */
public class FactoryMethod {

    public enum TypeDAO {
        EMPLEADO, DEPARTAMENTO , USUARIO
    }

    public static IDAO create(TypeDAO t) {
        IDAO dao = null;

        switch (t) {
            case EMPLEADO:
                dao = new EmpleadoDAO();
                break;
            case DEPARTAMENTO:
                dao = new DepartamentoDAO();
                break;
            case USUARIO:
                dao = new UsuariosDAO();
                break;
        }

        return dao;
    }
}
