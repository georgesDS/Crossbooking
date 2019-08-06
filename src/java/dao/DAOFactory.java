package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOFactory {
	/*
	 * LOGINS FOR CONNECTION (ne devrait pas etre ici ?!)
	 */
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost/";
	static final String bdd = "cb";
	static final String log = "root";
	static final String pass = "mysql";

	
	public static Connection getConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url + bdd, log, pass);

	}
	
	

	public static DAOBoite createDAOBoite() {

		return new DAOMySQLBoite();

	}

	
	public static DAOLivre createDAOLivre(){
		return new DAOMySQLivre();
		
	}
	
	
	public static DAOLecteur createDAOLecteur(){
		return new DAOMySQLLecteur();
		
	}
	
	public static DAOAvisLivre createDAOAvisLivre(){
		return new DAOMySQLAvisLivre();
		
	}
	
	public static DAOAvisBoite getDAOAVISBOITEALIVRES()
	{
		return new DAOMySQLAvisBoite();
		
	}
        
        //ajout par Catie pour UC EMPRUNT (trajet et Emprunt
        public static DAOTrajet createDAOTrajet()
        {
                return new DAOMysqlTrajet();
        }
        
	public static DAOEmprunt createDAOEmprunt()
        {
                return new DAOMySqlEmprunt();
        }
}
