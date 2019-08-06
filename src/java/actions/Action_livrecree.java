/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOFactory;
import dao.DAOLivre;
import forms.Form_CreerLivre;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Lecteur;
import models.Livre;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author j.gibily
 */
public class Action_livrecree extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "creationlivre_ok";

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
        
        HttpSession session = request.getSession();
       Lecteur L = (Lecteur) session.getAttribute("id_connexion");
        
         ActionForward forward=null;
        Form_CreerLivre FCL = (Form_CreerLivre) form; 
        Livre Liv = new Livre(FCL.getTitre(), FCL.getAuteur(), FCL.getEtat(),FCL.getGenre(),FCL.getStatut(), FCL.getImgLivre());
		
	DAOLivre daoL = DAOFactory.createDAOLivre();
	daoL.AjoutLivre(Liv, FCL.getIdboite(), L.getIdLecteur());
        
        request.setAttribute("creationlivre2",Liv);
        
      forward = mapping.findForward(SUCCESS);
      return forward; 
        
        
        
        
    }
}
