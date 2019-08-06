<%-- 
    Document   : mesemprunts
    Created on : 29 avr. 2016, 15:51:40
    Author     : j.gibily
--%>



<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.Boite"%>
<%@page import="models.Livre"%>
<%@include file='header.jsp' %>

<jsp:useBean id= "ListeMyEmprunts" scope="request" class= "HashMap<Livre,String>"/>




<main>

      <div id="main-container">
        <div id="fix"></div>
        <div id="main-content">
          <!-- CODEZ ICI SI pas formulaire -->
          <p id="E_introlivre1">Vous avez emprunté un livre via Crossbooking.<br>
          Vous pouvez le retrouver dans la liste ci-dessous.</p>
          
<p id="E_introlivre2">
            Si le livre que vous avez emprunté était absent de la boite, cliquez sur <span>?<span>
          </p>
          <p id="E_introlivre3">Vous êtes en possession actuellement de <span><%=ListeMyEmprunts.size()%></span> LIVRE(S) emprunté(s) à un autre membre de Crossbooking.
          </p>
          
        <%  Set<Map.Entry<Livre, String>> entreeListeEmprunt = ListeMyEmprunts.entrySet();
		Iterator<Map.Entry<Livre, String>> itertotoEmp = entreeListeEmprunt.iterator();
		while (itertotoEmp.hasNext()) {
			Map.Entry<Livre, String> liste2 = (Map.Entry) itertotoEmp.next();
			Livre li = liste2.getKey();
			String dateEmprunt = liste2.getValue(); %>
          
          
          
          <div class="E_meslivresemp">
            <span class=E_interrouge><img src="interrog.png" style="width:35px"/></span>
            <div class="E_imgidlivreemp">
                <img src="<%=li.getImglivre()%>" alt="<%=li.getTitre()%>"></img>
                <span class=E_idlivreemp>ID : <%=li.getIdlivre()%></span>
              </div>

              <div class="E_taglivreemp">
                  <span class=E_titrelivreemp><%=li.getTitre()%></span>
                  <span class=E_auteurlivreemp><%=li.getAuteur()%></span>
                  <span class=E_genrelivreemp><%=li.getGenre()%></span>
                </div>
<div class="E_aspectlivreemptotal">
                <div class="E_etatlivreemp">
                  <span class= "E_aspectlivreemp">Infos sur le livre / Aspect général :</span>
                  <span class= "E_desclivreemp"><%=li.getEtat()%></span>
                </div>
                <button class="E_Maj">Mettre à jour</button>
              </div>
                <div class="E_livreemp">
                  <span class=E_empemp>EMPRUNTÉ</span>
                  <span class=E_lelelele>le <%=dateEmprunt%></span>
                  <!--<span class=E_dateemprunt></span>-->

              
                  
                  
                  <button class="E_retournerlivre">RETOURNER</button>
                </div>


            </div>
          <%} %>

        </div>
      </div>




    </main>

<%@include file='footer.jsp' %>