package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ExceptionBDD;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import models.Lecteur;

public class DAOMySQLLecteur implements DAOLecteur{

	
	public int insert(Lecteur l) throws ExceptionBDD {

		
		try {
			String sql1 = "INSERT INTO lecteur(Nomlecteur, Prenomlecteur, Mail) values('" +l.getNomLecteur() + "','"+ l.getPrenomLecteur() + "','" + l.getMail() + "')";
			/*String sql2 = "SELECT Idlecteur from lecteur where Mail = '" + l.getMail()+"'";*/
			String sql1v2 = "INSERT INTO lecteur(Nomlecteur, Prenomlecteur, Mail) values(?,?,?)";
			Connection c = DAOFactory.getConnection();
                        PreparedStatement preparedStatement = c.prepareStatement(sql1v2, PreparedStatement.RETURN_GENERATED_KEYS);
			Statement s = c.createStatement();
			
			/*s.executeUpdate(sql1);*/
                        preparedStatement.setString(1,l.getNomLecteur());
                        preparedStatement.setString(2,l.getPrenomLecteur());
                        preparedStatement.setString(3,l.getMail());

                        preparedStatement.executeUpdate();
                        	
			/*ResultSet r2 = s.executeQuery(sql2);
			r2.next();
			int id = r2.getInt("Idlecteur");*/
                        
                        ResultSet rs = preparedStatement.getGeneratedKeys();
                        rs.next();
                        int id = rs.getInt(1);
                                
			/*r2.close();*/
			s.close();
			c.close();

			return id;
			
		} catch (InstantiationException e) {
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
	public String selectpropriolivre(int idlivre) throws ExceptionBDD {

		try {
			
			String pseudo;
			
			String sql = "select lecteur.Nomlecteur,lecteur.Prenomlecteur from depot,lecteur where depot.Idlivre ="+ idlivre + " and depot.idlecteur = lecteur.idlecteur";
			Connection cnx= DAOFactory.getConnection();
			java.sql.Statement stm = cnx.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
			res.next();
			
			pseudo = res.getString("Nomlecteur")+res.getString("Prenomlecteur");
			res.close();
			stm.close();
			cnx.close();

			return pseudo;
			
		} catch (InstantiationException e) {
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
	public String selectproprioboite(int idlecteur) throws ExceptionBDD {
		
		try {
			
			String pseudo;
			
			String sql = "select lecteur.Nomlecteur,lecteur.Prenomlecteur from lecteur where idlecteur ="+ idlecteur;
			Connection cnx= DAOFactory.getConnection();
			java.sql.Statement stm = cnx.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
			res.next();
			
			pseudo = res.getString("Nomlecteur")+res.getString("Prenomlecteur");
			res.close();
			stm.close();
			cnx.close();

			return pseudo;
			
		} catch (InstantiationException e) {
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
	public Lecteur connexion(String log, String pass) throws ExceptionBDD {
		
		try {
			String sql = "select * from lecteur";
			Connection cnx= DAOFactory.getConnection();
			java.sql.Statement stm = cnx.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
                        ArrayList<Lecteur> lecteurs = new ArrayList<Lecteur>();
			
                        while (res.next()) {
                            Lecteur l = new Lecteur(res.getInt("Idlecteur"),res.getString("Nomlecteur"),res.getString("Prenomlecteur"),res.getString("Mail"));
                            lecteurs.add(l);
			}
                        
                        res.close();
			stm.close();
			cnx.close();
                        
                        for (Lecteur l : lecteurs){
                            String login  = l.getMail();
                            String password = l.getNomLecteur()+l.getPrenomLecteur();
                            
                            if (log.equals(login) && pass.equals(password)){
                                return l;
                            }
                        }

			return null;
			
		} catch (InstantiationException e) {
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