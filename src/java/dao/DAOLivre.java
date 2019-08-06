package dao;

import java.util.HashMap;
import java.util.LinkedList;

import exception.ExceptionBDD;
import models.Boite;
import models.Lecteur;
import models.Livre;

public interface DAOLivre {

	public void AjoutLivre(Livre L, int idb, int idlecteur) throws ExceptionBDD;

	public int retourner(int idBoite, int idLivre) throws ExceptionBDD;

	public HashMap<Livre, Integer> select(int idlivre) throws ExceptionBDD;

	public LinkedList<Livre> selectcontboite(int idboite) throws ExceptionBDD;

	public int modiflivre(int idlivre, String statut) throws ExceptionBDD;

	public HashMap<Livre, String> selectEmprunt(int idlecteur) throws ExceptionBDD;
	
	public HashMap<Livre, Boite> selectMesLivresDispos(int idlecteur) throws ExceptionBDD;
	
	public HashMap<Livre,Lecteur> selectMesLivresNonDispos(int idlecteur) throws ExceptionBDD;
	
	public HashMap<Livre, Float> select() throws ExceptionBDD;
}
