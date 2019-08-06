/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOBoite;
import dao.DAOFactory;
import dao.DAOMySQLAvisLivre;
import dao.DAOMySQLLecteur;
import dao.DAOMySQLivre;
import forms.Form_Consulter_Livre;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Livre;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author r.marrast
 */
public class Action_Consulter_Un_Livre extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "consulterlivre_ok";

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
        Form_Consulter_Livre f = (Form_Consulter_Livre) form;
        
        int Idlivre = f.getIdlivre();
	DAOMySQLivre daom = (DAOMySQLivre) DAOFactory.createDAOLivre();
	HashMap<Livre, Integer> L = daom.select(Idlivre);
        request.setAttribute("livre_boite", L);
        
        DAOMySQLAvisLivre daoavis = (DAOMySQLAvisLivre) DAOFactory.createDAOAvisLivre();
        float note = daoavis.selectnote(f.getIdlivre());
        request.setAttribute("livre_note", note);
        
        DAOMySQLLecteur daolect = (DAOMySQLLecteur) DAOFactory.createDAOLecteur();
        String proprio = daolect.selectpropriolivre(f.getIdlivre());
        request.setAttribute("livre_proprio", proprio);
        
        return mapping.findForward(SUCCESS);
    }
}
