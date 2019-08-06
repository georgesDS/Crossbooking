/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import dao.DAOFactory;
import dao.DAOLecteur;
import forms.Form_Creer_Lecteur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Lecteur;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author j.dos-santos
 */
public class Action_Creer_Lecteur extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "creerlecteur_ok";

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
        
        
         // on initialise le forward
        ActionForward forward = null;
            
            
        Form_Creer_Lecteur f = (Form_Creer_Lecteur) form;
        
        Lecteur l = new Lecteur();
		
	l.setNomLecteur(f.getNomLecteur());
	l.setPrenomLecteur(f.getPrenomLecteur());
	l.setMail(f.getMonmail());
        
        DAOLecteur daol = DAOFactory.createDAOLecteur();
		int id = daol.insert(l);
				
		l.setIdLecteur(id);
        
        request.setAttribute("lecteurcree", l);
        
        forward = mapping.findForward(SUCCESS);
        
        return forward;
    }
}
