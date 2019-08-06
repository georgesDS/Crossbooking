/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ExceptionBDD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Emprunt;

/**
 *
 * @author Catherine
 */
//CREE PAR cATHERINE POUR FAIRE EMPRUNT LIVRE

public class DAOMySqlEmprunt implements DAOEmprunt {

    @Override
    public int creerEmprunt(Emprunt emp) throws ExceptionBDD  {
                try {
			//on force le num de lecteur et le num de livre car en vrai iol faudra les récuperer de l'écran
                        int idlecteur=2;
                        int idlivre=1;
			
			
			String sql = "INSERT INTO EMPRUNT(Idlivre,Idlecteur,Dateemprunt,Dateretour) values(" + emp.getIdlivre() + "," + emp.getIdlecteur()+ ",CURDATE()," + emp.getDateretour() + ")"  ;
			
			String sqlverif = "Select Numemprunt from EMPRUNT order by Numemprunt desc limit 1";

			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();

			// Requete  création emprunt
			s.executeUpdate(sql);
			// Requete  selection du dernier emprunt afin de recuperer le num de dernier emprunt
			ResultSet r = s.executeQuery(sqlverif);

			r.next();
			int numempruntverif = r.getInt("Numemprunt");

			r.close();
			s.close();
			c.close();
                        
                        //on retourne le numero d el'emprunt pour justifier que l'emprunt a bien ete realise
                        return numempruntverif;

		} 
        
                catch (InstantiationException e) {
			throw new ExceptionBDD();
		} catch (IllegalAccessException e) {
			throw new ExceptionBDD();
		} catch (ClassNotFoundException e) {
			throw new ExceptionBDD();
		} catch (SQLException e) {
			throw new ExceptionBDD();
		}
       }

    }
    
    
        

         
   
        
  
    