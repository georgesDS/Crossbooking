/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOAvisBoite;
import dao.DAOBoite;
import dao.DAOFactory;
import dao.DAOLecteur;
import dao.DAOLivre;
import forms.Form_Boite_Consulte;
import forms.Form_Consulter_Boite;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Boite;
import models.Livre;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Catherine
 */
public class Action_Consulter_UneBoite extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "consulteruneboite_ok";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //on initialise le forward à null
        ActionForward forward = null;
        //en fait on construit le formulaire à partir de celui qui est transmis
        //sous la forme d'un ActionForm dans le execute et on le met au format 
        //du formulaire Form_Consulter_Une_Boite qui permet de recuperer le 
        //numero de boites à livres qu'on veut consulter
        //
        Form_Consulter_Boite fbal = (Form_Consulter_Boite)form;
        int numbal = fbal.getIdboite();
        
        //Recherche de la boite concernée dans la bd 
        //et recupération de tous ses composants
        // la boite, la liste des livres qui est dedans, l'avis des utilisateurs sur la boite
        // pour calculer sa note, idendité du lecteur qui l'a créé,
        DAOBoite daobal=DAOFactory.createDAOBoite();
        Boite balconsult = daobal.select(numbal);
	DAOLivre daolivre=DAOFactory.createDAOLivre();
	LinkedList<Livre> listelivres=daolivre.selectcontboite(numbal);
        DAOAvisBoite daoavisbal=DAOFactory.getDAOAVISBOITEALIVRES();
	float moy=daoavisbal.calcmoy(numbal);
        DAOLecteur daol = DAOFactory.createDAOLecteur();
        String proprio=daol.selectproprioboite(balconsult.getIdlecteur());
        
        //on va se servir d'un formulaire pour afficher toutes ses infos.
        // comme tout à l'heure on construit le formulaire à partir de celui 
        //qui est transmis sous la forme d'un ActionForm dans le execute
        //et on le met au format du formulaire Form_Boite_Consulte
        //et on appelle la fonction remplir qui va remplir ce formulaire
        //et on va placer ce formulaire dans le HTTPREQUEST
        
        Form_Boite_Consulte fbalconsult = new Form_Boite_Consulte();
        fbalconsult.remplir (balconsult, listelivres,moy,proprio);
        
        request.setAttribute("essaicatie", fbalconsult);
        forward=mapping.findForward(SUCCESS);
        
        return forward;
        
    }
}
