package dao;

import java.sql.Connection;
import java.sql.SQLException;

import exception.ExceptionBDD;

public class DAOMySQLAvisLivre implements DAOAvisLivre {

	@Override
	public float selectnote(int idlivre) throws ExceptionBDD {

		try {			
			String sql = "Select avg(notelivre) from avis_LIVRE where Idlivre="+ idlivre;
			Connection cnx= DAOFactory.getConnection();
			java.sql.Statement stm = cnx.createStatement();
			java.sql.ResultSet res = stm.executeQuery(sql);
			
			res.next();
			
			float note = res.getFloat(1);
			
			res.close();
			stm.close();
			cnx.close();
			
			return note;
			
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
