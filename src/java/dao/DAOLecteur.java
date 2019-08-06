package dao;

import exception.ExceptionBDD;
import models.Lecteur;

public interface DAOLecteur {

	public int insert(Lecteur l) throws ExceptionBDD;
	public String selectpropriolivre(int idlivre) throws ExceptionBDD;
	public String selectproprioboite(int idboite) throws ExceptionBDD;
        public Lecteur connexion(String log, String pass) throws ExceptionBDD;
	

}