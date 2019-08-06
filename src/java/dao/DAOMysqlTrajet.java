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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import models.Boite;
import models.Livre;

/**
 *
 * @author Catherine
 */

//DAO CREE PAR CATIE (pour emprunt)
public class DAOMysqlTrajet implements DAOTrajet {
    
    public int modiftrajet(int idlivre, int idboite, Date datesortie) throws ExceptionBDD{
        
                try{
                    //on force l'ident de la boite à livres et l'ident du livre car...en fait il faut les capturer de l'écran car on est 
                    //positionné sur une boite à livres et sur un livre
                    
                    
                    String sql = "UPDATE TRAJET SET Datesortieboite = CURDATE() WHERE Datesortieboite IS NULL AND Idlivre ="+ idlivre+ " AND Idboite="+idboite;
                    
                    
                    Connection c = DAOFactory.getConnection();
                    Statement s = c.createStatement();
                    
                    int req = s.executeUpdate(sql);
                    
                    s.close();
                    c.close();
			
                    return req;
                    
                    
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

    @Override
    public ArrayList<Boite> trajet(int idlivre) throws ExceptionBDD {
        
        try{
                                        
                    String sql = "SELECT trajet.idboite,geox,geoy from trajet, boite_a_livres where trajet.idboite = boite_a_livres.idboite and trajet.Idlivre =";
                    
                    
                    Connection c = DAOFactory.getConnection();
                    Statement s = c.createStatement();
                    ResultSet r = s.executeQuery(sql+idlivre);
                    
                    ArrayList<Boite> trajetlivre = new ArrayList<Boite>();
                    
                    while(r.next()){
                        Boite b = new Boite(r.getInt("idboite"),r.getFloat("geox"),r.getFloat("geoy"));
                        trajetlivre.add(b);

                    }
                    r.close();
                    s.close();
                    c.close();
			
                    return trajetlivre;
                
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
