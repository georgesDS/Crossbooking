/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOBoite;
import dao.DAOFactory;
import forms.Form_Creer_Boite;
import forms.Form_Creer_Lecteur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Boite;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author r.marrast
 */
public class Action_Creer_Boite extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "creerboite_ok";

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
        Form_Creer_Boite  f = (Form_Creer_Boite) form;
        
        
        Boite b = new Boite(f.getVille(), f.getDepartement(), f.getAdresse(), f.getPlagehoraire(),
                        f.getContenancemaxi(), f.getGeox(), f.getGeoy(), f.getImgboite(), f.getIdlecteur());

        DAOBoite dao = DAOFactory.createDAOBoite();

        b.setIdboite(dao.insert(b));
        
        request.setAttribute("boite", b);
      
        return mapping.findForward(SUCCESS);
    }
}
