/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ExceptionBDD;
import models.Emprunt;

/**
 *
 * @author Catherine
 */
public interface DAOEmprunt {
    public int creerEmprunt(Emprunt e)throws ExceptionBDD;
    
}
