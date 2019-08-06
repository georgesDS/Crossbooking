/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ExceptionBDD;
import java.util.ArrayList;
import java.util.Date;
import models.Boite;

/**
 *
 * @author Catherine
 */
public interface DAOTrajet {
    

	
	 //ajout catie pour modif trajet en cas emprunt
        public int modiftrajet(int idlivre, int idboite, Date datesortie) throws ExceptionBDD;
        public ArrayList<Boite> trajet(int idlivre) throws ExceptionBDD;
}
    

