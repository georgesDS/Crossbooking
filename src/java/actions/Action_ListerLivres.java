/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOFactory;
import dao.DAOLivre;
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
public class Action_ListerLivres extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "listelivres_ok";

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
        DAOLivre daol = DAOFactory.createDAOLivre();

        HashMap<Livre, Float> map =  daol.select();

        request.setAttribute("listelivres", map);
        forward = mapping.findForward(SUCCESS);

        return forward;
    }
}
