/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOBoite;
import dao.DAOFactory;
import java.util.ArrayList;
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
public class Action_Consulter_Boites extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "listeboites_ok";

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
        
        DAOBoite daobal = DAOFactory.createDAOBoite();
	ArrayList<Boite> bal = daobal.select();

        request.setAttribute("listeboites", bal);
        
        return mapping.findForward(SUCCESS);
    }
}
