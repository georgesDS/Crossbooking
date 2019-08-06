package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ExceptionBDD;

public class DAOMySQLAvisBoite implements DAOAvisBoite{

	
	@Override
	public float calcmoy(int numbal)
			throws ExceptionBDD {
		
		
		try {
			float moyavis=0;
			String sql1="select avg(NoteBoite) moy from AVISBOITEALIVRES where Idboite=" + numbal;	
			Connection cnx=DAOFactory.getConnection();
			Statement sta=cnx.createStatement();
			ResultSet res=sta.executeQuery(sql1);
			
			while (res.next())
			{
			moyavis=res.getFloat("moy");
			}
			
			return moyavis;
			
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

	
	


