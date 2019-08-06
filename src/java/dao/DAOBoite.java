package dao;

import java.util.ArrayList;
import java.util.HashMap;

import exception.ExceptionBDD;
import models.Boite;

public interface DAOBoite {
	
	public int insert(Boite b) throws ExceptionBDD;
	public ArrayList<Boite>select() throws ExceptionBDD;
        public ArrayList<Boite>select1(int Idlecteur) throws ExceptionBDD;
	public Boite select(int idboite) throws ExceptionBDD;
	public HashMap<Boite, Float> selectmesboites(int idlecteur) throws ExceptionBDD;
	public HashMap<Boite, Integer> nblivresdansboite(ArrayList<Boite> boites) throws ExceptionBDD;
}
