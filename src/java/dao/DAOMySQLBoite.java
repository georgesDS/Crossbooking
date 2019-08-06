package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import exception.ExceptionBDD;
import java.sql.PreparedStatement;
import models.Boite;

public class DAOMySQLBoite implements DAOBoite {

	@Override
	public int insert(Boite b) throws ExceptionBDD {

		try {

			String sql1 = "INSERT INTO boite_a_livres(ville,departement,adresse,plagehoraire,contenancemaxi,geox,geoy,imgboite,idlecteur) values('"
					+ b.getVille() + "','" + b.getDepartement() + "','" + b.getAdresse() + "','" + b.getPlagehoraire()
					+ "'," + b.getContenancemaxi() + "," + b.getGeox() + "," + b.getGeoy() + ",'" + b.getImgboite()
					+ "'," + b.getIdlecteur() + ")";
			String sql2 = "Select idboite from boite_a_livres order by idboite desc limit 1";
                        String sql1v2 = "INSERT INTO boite_a_livres(ville,departement,adresse,plagehoraire,contenancemaxi,geox,geoy,imgboite,idlecteur) values(?,?,?,?,?,?,?,?,?)";            
			
                        
                        Connection c = DAOFactory.getConnection();
                        
                        PreparedStatement preparedStatement = c.prepareStatement(sql1v2); 
			Statement s = c.createStatement();
                        
                        preparedStatement.setString(1,b.getVille());
                        preparedStatement.setString(2,b.getDepartement());
                        preparedStatement.setString(3,b.getAdresse());
                        preparedStatement.setString(4,b.getPlagehoraire());
                        preparedStatement.setInt(5,b.getContenancemaxi());
                        preparedStatement.setFloat(6, b.getGeox());
                        preparedStatement.setFloat(7, b.getGeoy());
                        preparedStatement.setString(8, b.getImgboite());
                        preparedStatement.setInt(9,b.getIdlecteur());
                        preparedStatement.executeUpdate();
			// Requete 1, insertion boite
			/*s.executeUpdate(sql1);*/
			// Requete 2, selection idboite
			ResultSet r = s.executeQuery(sql2);

			r.next();
			int id = r.getInt("idboite");

			r.close();
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
public ArrayList<Boite> select1(int Idlecteur) throws ExceptionBDD {

		try {

			ArrayList<Boite> ListedeBoite = new ArrayList<Boite>();
			String sql = "SELECT * from boite_a_livres ;";

			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				int Idboite = r.getInt("Idboite");
				Date Datecreation = r.getDate("Datecreation");
				String Ville = r.getString("Ville");
				String Departement = r.getString("Departement");
				String Adresse = r.getString("Adresse");
				String Plagehoraire = r.getString("Plagehoraire");
				int Contenancemaxi = r.getInt("Contenancemaxi");
				Float Geox = r.getFloat("Geox");
				Float Geoy = r.getFloat("Geoy");
				String ImgBoite = r.getString("ImgBoite");

				Boite b = new Boite();

				b.setIdboite(Idboite);
				b.setDatecreation(Datecreation);
				b.setVille(Ville);
				b.setDepartement(Departement);
				b.setAdresse(Adresse);
				b.setPlagehoraire(Plagehoraire);
				b.setContenancemaxi(Contenancemaxi);
				b.setGeox(Geox);
				b.setGeoy(Geoy);
				b.setIdboite(Idboite);
				b.setImgboite(ImgBoite);

				ListedeBoite.add(b);

			}

			r.close();
			s.close();
			c.close();
			return ListedeBoite;

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
	public ArrayList<Boite> select() throws ExceptionBDD {

		try {

			ArrayList<Boite> ListedeBoite = new ArrayList<Boite>();
			String sql = "SELECT * from boite_a_livres ;";

			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(sql);

			while (r.next()) {
				int Idboite = r.getInt("Idboite");
				Date Datecreation = r.getDate("Datecreation");
				String Ville = r.getString("Ville");
				String Departement = r.getString("Departement");
				String Adresse = r.getString("Adresse");
				String Plagehoraire = r.getString("Plagehoraire");
				int Contenancemaxi = r.getInt("Contenancemaxi");
				Float Geox = r.getFloat("Geox");
				Float Geoy = r.getFloat("Geoy");
				String ImgBoite = r.getString("ImgBoite");

				Boite b = new Boite();

				b.setIdboite(Idboite);
				b.setDatecreation(Datecreation);
				b.setVille(Ville);
				b.setDepartement(Departement);
				b.setAdresse(Adresse);
				b.setPlagehoraire(Plagehoraire);
				b.setContenancemaxi(Contenancemaxi);
				b.setGeox(Geox);
				b.setGeoy(Geoy);
				b.setIdboite(Idboite);
				b.setImgboite(ImgBoite);

				ListedeBoite.add(b);

			}

			r.close();
			s.close();
			c.close();
			return ListedeBoite;

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

	public Boite select(int idboite) throws ExceptionBDD {
		// TODO Auto-generated method stub

		try {

			String sql1 = "select * from boite_a_livres where Idboite=" + idboite;

			Connection cnx = DAOFactory.getConnection();
			Statement sta = cnx.createStatement();
			ResultSet res = sta.executeQuery(sql1);

			res.next();

			int idBoite = res.getInt("Idboite");
			Date datecreation = res.getDate("Datecreation");
			String ville = res.getString("Ville");
			String departement = res.getString("Departement");
			String adresse = res.getString("Adresse");
			String plagehoraire = res.getString("Plagehoraire");
			int contenancemaxi = res.getInt("Contenancemaxi");
			float geox = res.getFloat("Geox");
			float geoy = res.getFloat("Geoy");
			String imgBoite = res.getString("Imgboite");
			int lectprop = res.getInt("Idlecteur");

			Boite boiteconsult = new Boite(idBoite, datecreation, ville, departement, adresse, plagehoraire,
					contenancemaxi, geox, geoy, imgBoite, lectprop);

			res.close();
			sta.close();
			cnx.close();
			return boiteconsult;

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
	public HashMap<Boite, Float> selectmesboites(int idlecteur) throws ExceptionBDD {
		try {

			HashMap<Boite, Float> ListemesBoite = new HashMap<Boite, Float>();
			ArrayList<Boite> boites = new ArrayList<Boite>();
			String sql = "select * from boite_a_livres where idlecteur=" + idlecteur;
			String sql2 = "select avg(noteboite) as moy from avisboitealivres where idboite=";

			Connection cnx = DAOFactory.getConnection();
			Statement sta = cnx.createStatement();
			ResultSet res = sta.executeQuery(sql);

			while (res.next()) {
				int idBoite = res.getInt("Idboite");
				Date datecreation = res.getDate("Datecreation");
				String ville = res.getString("Ville");
				String departement = res.getString("Departement");
				String adresse = res.getString("Adresse");
				String plagehoraire = res.getString("Plagehoraire");
				int contenancemaxi = res.getInt("Contenancemaxi");
				float geox = res.getFloat("Geox");
				float geoy = res.getFloat("Geoy");
				String imgBoite = res.getString("Imgboite");
				int lectprop = res.getInt("Idlecteur");

				Boite boiteconsult = new Boite(idBoite, datecreation, ville, departement, adresse, plagehoraire,
						contenancemaxi, geox, geoy, imgBoite, lectprop);
				boites.add(boiteconsult);
			}

			res.close();
			sta.close();

			for (Boite b : boites) {
				Statement sta2 = cnx.createStatement();
				ResultSet res2 = sta2.executeQuery(sql2 + b.getIdboite());
				res2.next();
				float moy = res2.getFloat("moy");
				ListemesBoite.put(b, moy);
				res2.close();
				sta2.close();
			}

			cnx.close();
			return ListemesBoite;

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
	public HashMap<Boite, Integer> nblivresdansboite(ArrayList<Boite> boites) throws ExceptionBDD {

		try {
			HashMap<Boite, Integer> ListemesBoite = new HashMap<Boite, Integer>();
			String sql = "select count(*) as cpt from trajet where datesortieboite is null and idboite=";

			Connection cnx = DAOFactory.getConnection();

			for (Boite b : boites) {
				Statement sta = cnx.createStatement();
				ResultSet res = sta.executeQuery(sql + b.getIdboite());
				res.next();
				int cpt = res.getInt("cpt");
				ListemesBoite.put(b, cpt);
				res.close();
				sta.close();
			}

			cnx.close();
			return ListemesBoite;

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
