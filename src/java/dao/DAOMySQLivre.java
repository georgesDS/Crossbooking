package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import exception.ExceptionBDD;
import java.sql.PreparedStatement;
import models.Boite;
import models.Lecteur;
import models.Livre;

public class DAOMySQLivre implements DAOLivre {

	@Override
	public void AjoutLivre(Livre L, int idb, int idlecteur) throws ExceptionBDD {

		try {
			String sql = "INSERT INTO livre(Titre,Auteur,Etat,Statut,Genre,Imglivre) values('" + L.getTitre() + "','"
					+ L.getAuteur() + "','" + L.getEtat() + "','" + L.getStatut() + "','" + L.getGenre() + "','"
					+ L.getImglivre() + "')";

			// Je recupere l'id du dernier livre ajouté.
			String sql1 = "SELECT Idlivre from LIVRE order by Idlivre desc limit 1 ; ";
                        String sqlv2 = "INSERT INTO livre(Titre,Auteur,Etat,Statut,Genre,Imglivre) values(?,?,?,?,?,?)";
                        
                        
			Connection c = DAOFactory.getConnection();
                        PreparedStatement preparedStatement = c.prepareStatement(sqlv2); 
			Statement s = c.createStatement();
			/*s.executeUpdate(sql);*/
                        preparedStatement.setString(1,L.getTitre());
                        preparedStatement.setString(2,L.getAuteur());
                        preparedStatement.setString(3,L.getEtat());
                        preparedStatement.setString(4,L.getStatut());
                        preparedStatement.setString(5,L.getGenre());
                        preparedStatement.setString(6,L.getImglivre());

                        preparedStatement.executeUpdate();
			ResultSet r = s.executeQuery(sql1);

			r.next();
			int Idl = r.getInt("Idlivre");
			L.setIdlivre(Idl);

			r.close();
			s.close();
			c.close();

			String sql2 = "INSERT INTO DEPOT (Idlivre,Idlecteur) values (" + Idl + "," + idlecteur + ")";
			Connection c2 = DAOFactory.getConnection();
			Statement s2 = c2.createStatement();
			s2.executeUpdate(sql2);
			s2.close();
			c2.close();

			String sql3 = "INSERT INTO TRAJET (Idlivre,Idboite) VALUES(" + Idl + "," + idb + ")";
			Connection c3 = DAOFactory.getConnection();
			Statement s3 = c3.createStatement();
			s3.executeUpdate(sql3);
			s3.close();
			c3.close();
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
	public int retourner(int idBoite, int idLivre) throws ExceptionBDD {

		try {

			String sql1 = "INSERT INTO TRAJET (idlivre, idboite) VALUES(" + idLivre + "," + idBoite + ")";
			String sql2 = "UPDATE EMPRUNT SET Dateretour = CURDATE() WHERE Dateretour IS NULL AND Idlivre =" + idLivre;
			String sql3 = "UPDATE LIVRE SET Statut='DISPONIBLE' WHERE Idlivre =" + idLivre;

			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();

			// Requete 1, mise à jour de TRAJET avec l'info sur le stockage du
			// livre.
			int req1 = s.executeUpdate(sql1);
			// Requete 2, mise à jour de EMPRUNT avec la cloture de l'emprunt,
			// date du jour.
			int req2 = s.executeUpdate(sql2);
			// Requete 3, mise à jour de LIVRE avec le statut à jour.
			int req3 = s.executeUpdate(sql3);

			s.close();
			c.close();

			return req1 + req2 + req3;

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
	public HashMap<Livre, Integer> select(int idlivre) throws ExceptionBDD {

		try {

			HashMap<Livre, Integer> map = new HashMap<>();

			String sql = "Select * from LIVRE where Idlivre=" + idlivre;
			Connection cnx = DAOFactory.getConnection();
			java.sql.Statement stm = cnx.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);

			res.next();

			int res_idlivre = res.getInt("Idlivre");
			String res_titre = res.getString("Titre");
			String res_auteur = res.getString("Auteur");
			String res_etat = res.getString("Etat");
			String res_genre = res.getString("Genre");
			String res_imglivre = res.getString("Imglivre");

			res.close();
			stm.close();
			cnx.close();

			String sqldispo = "Select * from TRAJET WHERE Idlivre=" + idlivre + " Order by NumTrajet DESC LIMIT 1";
			Connection cnx2 = DAOFactory.getConnection();
			java.sql.Statement stm2 = cnx2.createStatement();
			java.sql.ResultSet res2 = stm2.executeQuery(sqldispo);
			res2.next();

			String statut;
			int idboite = 0;
			if (res2.getDate("Datesortieboite") == null) {
				statut = "DISPONIBLE";
				idboite = res2.getInt("idboite");
			} else {
				statut = "INDISPONIBLE";
				idboite = -1;
			}

			res2.close();
			stm2.close();
			cnx2.close();

			Livre L = new Livre(res_idlivre, res_titre, res_auteur, res_etat, statut, res_genre, res_imglivre);

			map.put(L, idboite);

			return map;

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

	public LinkedList<Livre> selectcontboite(int idboite) throws ExceptionBDD {
		// TODO Auto-generated method stub

		try {

			String sql1 = "SELECT livre.idlivre, livre.titre, livre.genre from livre,trajet where livre.idlivre = trajet.idlivre and trajet.idboite ="
					+ idboite + " and Datesortieboite is null";
			Connection cnx = DAOFactory.getConnection();
			Statement sta = cnx.createStatement();
			ResultSet res = sta.executeQuery(sql1);

			LinkedList<Livre> listlivreboite = new LinkedList<Livre>();

			while (res.next()) {
				String Titre = res.getString("Titre");
				String Genre = res.getString("Genre");
                                int idlivre = res.getInt("Idlivre");
				Livre livre = new Livre();

				livre.setTitre(Titre);
				livre.setGenre(Genre);
                                livre.setIdlivre(idlivre);

				listlivreboite.add(livre);
			}

			res.close();
			sta.close();
			cnx.close();
			return listlivreboite;

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

	// fonction catie pour emprunter livre
	public int modiflivre(int idlivre, String statut) throws ExceptionBDD {

		try {
			// on force l'ident du livre...en fait il faut le capturer de
			// l'écran car on est
			// positionné sur ce livre

			String sql = "UPDATE LIVRE SET Statut='" + statut + "' WHERE Idlivre =" + idlivre;

			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();

			int req = s.executeUpdate(sql);

			s.close();
			c.close();

			return req;

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

	public HashMap<Livre, String> selectEmprunt(int idlecteur) throws ExceptionBDD {

		try {
			HashMap<Livre, String> Listedef = new HashMap();
			ArrayList<Livre> listeLivreEmprunt = new ArrayList();
			// ArrayList<String> listeDate = new ArrayList();

			

			String sql1 = "SELECT Idlivre,Titre, Auteur, Genre, Imglivre, Etat from livre where Idlivre in(S"
					+ "ELECT Idlivre from emprunt where Idlecteur=" + idlecteur +");";
			Connection c1 = DAOFactory.getConnection();
			Statement s1 = c1.createStatement();
			ResultSet r1 = s1.executeQuery(sql1);

			while (r1.next()) {
				int Idl = r1.getInt("Idlivre");
				String titre = r1.getString("Titre");
				String auteur = r1.getString("Auteur");
				String genre = r1.getString("Genre");
				String imglivre = r1.getString("Imglivre");
				String etat = r1.getString("Etat");

				Livre L = new Livre();

				L.setIdlivre(Idl);
				L.setTitre(titre);
				L.setAuteur(auteur);
				L.setGenre(genre);
				L.setImglivre(imglivre);
				L.setEtat(etat);

				listeLivreEmprunt.add(L);
			}

			c1.close();
			s1.close();
			r1.close();

			String sql2 = "Select Dateemprunt from emprunt where dateretour is null and Idlivre=";

			for (Livre liv : listeLivreEmprunt) {

				Connection c2 = DAOFactory.getConnection();
				Statement s2 = c2.createStatement();
				ResultSet r2 = s2.executeQuery(sql2 + liv.getIdlivre());

				while (r2.next()) {

					String dateEmprunt = r2.getString("Dateemprunt");
					Listedef.put(liv, dateEmprunt);
				}

				c2.close();
				s2.close();
				r2.close();

			}

			return Listedef;

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

	public HashMap<Livre, Boite> selectMesLivresDispos(int idlecteur) throws ExceptionBDD {

		try {
			

			HashMap<Livre, Boite> ListeMesLivresDispos = new HashMap<Livre, Boite>();
			ArrayList<Livre> ListeLivreDispo = new ArrayList<Livre>();
			ArrayList<Boite> ListeBoiteDispo = new ArrayList<Boite>();

			String sql1 = "SELECT Idlivre, Titre, Auteur, Genre, Imglivre, Etat, Statut FROM  livre WHERE Idlivre in (SELECT Idlivre FROM depot WHERE Idlecteur= "
					+ idlecteur + ") AND Statut='DISPONIBLE';";

			Connection c1 = DAOFactory.getConnection();
			Statement s1 = c1.createStatement();
			ResultSet r1 = s1.executeQuery(sql1);

			while (r1.next()) {

				int Idlivre = r1.getInt("Idlivre");
				String Titre = r1.getString("Titre");
				String Auteur = r1.getString("Auteur");
				String Genre = r1.getString("Genre");
				String Imglivre = r1.getString("Imglivre");
				String Etat = r1.getString("Etat");
				Livre L = new Livre();
				L.setIdlivre(Idlivre);
				L.setTitre(Titre);
				L.setAuteur(Auteur);
				L.setGenre(Genre);
                                L.setImglivre(Imglivre);
				L.setEtat(Etat);

				ListeLivreDispo.add(L);
			}

			r1.close();
			s1.close();
			c1.close();

			String sql2 = "SELECT Idboite, ville, departement FROM boite_a_livres where Idboite in (SELECT Idboite FROM trajet where Idlivre=";

			for (Livre liv : ListeLivreDispo) {

				Connection c2 = DAOFactory.getConnection();
				Statement s2 = c2.createStatement();
				ResultSet r2 = s2.executeQuery(sql2+liv.getIdlivre()+")");

				while (r2.next()) {

					int Idboite = r2.getInt("Idboite");
					String Ville = r2.getString("ville");
					String Departement = r2.getString("departement");
					Boite B = new Boite();
					B.setIdboite(Idboite);
					B.setVille(Ville);
					B.setDepartement(Departement);

					ListeBoiteDispo.add(B);
					ListeMesLivresDispos.put(liv, B);
				}

				r2.close();
				s2.close();
				c2.close();

			}

			return ListeMesLivresDispos;
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
	
	
	

	public HashMap<Livre, Lecteur> selectMesLivresNonDispos(int idlecteur)
			throws ExceptionBDD {

		
		try {
			

			HashMap<Livre, Lecteur> ListeMesLivresNonDispos = new HashMap<Livre, Lecteur>();
			ArrayList<Livre> ListeNonDispos = new ArrayList<Livre>();
			ArrayList<Lecteur> ListeLecteur = new ArrayList<Lecteur>();

			String sql1 = "SELECT Idlivre, Titre, Auteur, Genre, Imglivre, Etat, Statut FROM  livre WHERE Idlivre in (SELECT Idlivre FROM depot WHERE Idlecteur= "
					+ idlecteur + ") AND Statut='INDISPONIBLE';";

			Connection c1 = DAOFactory.getConnection();
			Statement s1 = c1.createStatement();
			ResultSet r1 = s1.executeQuery(sql1);

			while (r1.next()) {

				int Idlivre = r1.getInt("Idlivre");
				String Titre = r1.getString("Titre");
				String Auteur = r1.getString("Auteur");
				String Genre = r1.getString("Genre");
				String Imglivre = r1.getString("Imglivre");
				String Etat = r1.getString("Etat");

				Livre livre = new Livre();

				livre.setIdlivre(Idlivre);
				livre.setTitre(Titre);
				livre.setAuteur(Auteur);
				livre.setGenre(Genre);
                                livre.setImglivre(Imglivre);
				livre.setEtat(Etat);

				ListeNonDispos.add(livre);

			}

			r1.close();
			s1.close();
			c1.close();

			String sql2 = "SELECT Nomlecteur FROM lecteur where Idlecteur in (SELECT Idlecteur from emprunt where dateretour is null and Idlivre=";

			for (Livre l : ListeNonDispos) {

				Connection c2 = DAOFactory.getConnection();
				Statement s2 = c2.createStatement();
				ResultSet r2 = s2.executeQuery(sql2+l.getIdlivre()+")");

				while (r2.next()) {

					String nomLecteur = r2.getString("Nomlecteur");
					Lecteur lecteur = new Lecteur();
					lecteur.setNomLecteur(nomLecteur);

					ListeLecteur.add(lecteur);

					ListeMesLivresNonDispos.put(l, lecteur);

				}
			}
			return ListeMesLivresNonDispos;
				
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
	
	
	
	public HashMap<Livre, Float> select() throws ExceptionBDD {
		
		try {
			String sql = "SELECT livre.*, avg(noteLivre) as moy FROM livre LEFT JOIN avis_livre ON livre.Idlivre = avis_livre.Idlivre GROUP BY livre.Idlivre";
			
			HashMap<Livre, Float> liste = new HashMap<Livre, Float>();
			
			
			Connection c = DAOFactory.getConnection();
			Statement s = c.createStatement();
					
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()){
				Float etoiles = r.getFloat("moy");
				Livre truc = new Livre(r.getInt("Idlivre"),r.getString("Titre"),r.getString("Auteur"),
						r.getString("Genre"),r.getString("Imglivre") );
				liste.put(truc, etoiles) ;
				}
			
			r.close();
			s.close();
			c.close();

			return liste;
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
