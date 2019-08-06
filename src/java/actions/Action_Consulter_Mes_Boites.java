/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import dao.DAOMySQLBoite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Boite;
import models.Lecteur;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author r.marrast
 */
public class Action_Consulter_Mes_Boites extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "mesboites_ok";

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
        ActionForward forward = null;
        
        DAOMySQLBoite dao = new DAOMySQLBoite();
        HashMap<Boite,Float> mesboites = dao.selectmesboites(L.getIdLecteur());
        request.setAttribute("mesboites", mesboites);
        
        ArrayList<Boite> liste = new ArrayList<Boite>();
        Iterator<Entry<Boite, Float>> entries = mesboites.entrySet().iterator();
        while (entries.hasNext()) {
                Entry<Boite, Float> thisEntry = entries.next();
                Boite b = (Boite) thisEntry.getKey();
                liste.add(b);
        }

        HashMap<Boite, Integer> mescptboites = dao.nblivresdansboite(liste);
        
        request.setAttribute("mescptboites", mescptboites);

        return mapping.findForward(SUCCESS);
    }
}
