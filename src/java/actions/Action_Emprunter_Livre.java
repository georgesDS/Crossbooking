/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOEmprunt;
import dao.DAOFactory;
import dao.DAOLivre;
import dao.DAOTrajet;
import forms.Form_Emprunt;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Emprunt;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author r.marrast
 */
public class Action_Emprunter_Livre extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "emprunt_ok";

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
        
        ActionForward forward = null;
        Form_Emprunt femp = (Form_Emprunt) form;
        
        /* EMPRUNT */
        
        Emprunt emp = new Emprunt(femp.getIdlivre(),femp.getIdlecteur(),new Date());
	DAOEmprunt daoemp = DAOFactory.createDAOEmprunt();
		
        int numemprunt = daoemp.creerEmprunt(emp);
        emp.setNumemprunt(numemprunt);
        
        request.setAttribute("emprunt", emp);
        
        
        
        /* TRAJET */

        Date datesortie= new Date();
        int idlivre=femp.getIdlivre();
        int idboite=femp.getIdboite();

        DAOTrajet daotraj=DAOFactory.createDAOTrajet();
        daotraj.modiftrajet(idlivre,idboite,datesortie);
        
        
        /* STATUT du livre */
        
        String statut= "INDISPONIBLE";

        DAOLivre daoliv=DAOFactory.createDAOLivre();
        daoliv.modiflivre(idlivre,statut);
        
        return mapping.findForward(SUCCESS);
    }
}
