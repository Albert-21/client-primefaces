/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.factory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaddiel
 */
public interface IDAO<T> {

    public boolean insert(T pojo);

    public boolean update(T pojo);

    public T searchById(long id);

    public List<T> showAll();

    public boolean delete(long id);

}
