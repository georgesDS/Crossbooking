<%-- 
    Document   : meslivres
    Created on : 28 avr. 2016, 16:59:41
    Author     : j.gibily
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="models.Lecteur"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.Livre"%>
<%@page import="models.Boite"%>
<%@include file='header.jsp' %>

<jsp:useBean id="meslivresdispos" scope="request" class= "HashMap<Livre, Boite>"/>
<jsp:useBean id="meslivresnondispos" scope="request" class= "HashMap<Livre, Lecteur>"/>

<main>

  
    
      <div id="main-container">
        
          <div id="fix"></div>
          <div id="main-content">
        
          <!-- CODEZ ICI SI pas formulaire -->
          <p id="introlivre1">Où sont mes livres ? Qui les détient actuellement ? Dans quel état sont-ils ?
            Trouvez toutes les réponses à vos questions sur cette page.
          </p>
          
          <p id="introlivre2">Les lecteurs de Crossbooking s'échangent actuellement <span><%=meslivresdispos.size()+meslivresnondispos.size()%></span> de vos livres:
          </p>
         
         
        
          
          
              
              <% Set<Map.Entry<Livre, Boite>> entreeboite = meslivresdispos.entrySet(); 
		Iterator<Map.Entry<Livre, Boite>> itertoto = entreeboite.iterator();
		while (itertoto.hasNext()) {
			Map.Entry<Livre, Boite> liste = (Map.Entry) itertoto.next();
			Livre li = liste.getKey();
			Boite b = liste.getValue();%>
  <div class="meslivredispo">
		<span class=croixrouge><img src="croix.png" style="width:25px"/></span>
            <div class="imgidlivredispo">
                <img src="<%=li.getImglivre()%>">
                <span class="idlivredispo">ID : <%=li.getIdlivre()%></span>
              </div>
              <div class="taglivredispo">
                  <span class="titrelivredispo"><%=li.getTitre()%></span>
                  <span class="auteurlivredispo"><%=li.getAuteur()%></span>
                  <span class="genrelivredispo"><%=li.getGenre()%></span>
                </div>
                <div class="etatlivredispo">
                  <span class= "aspectlivre">Infos sur le livre / Aspect général :</span>
                  <span class= "desclivre"><%=li.getEtat()%></span>
                </div>
			
            
            
            <div class="boitelivredispo">
                  <span class=dispodispo>DISPONIBLE</span>
                  <span class=numboitedispo>Boîte n°<%=b.getIdboite()%></span>
                  <span class=villeboitedispo><%=b.getVille()%></span>
                  <span class=dptboitedispo><%=b.getDepartement()%></span>
                </div>
                <img src="imglocboitedispo.jpg" ></img>
              
              </div>
              
             <%} %>
            

             
                 
               <%  Set<Map.Entry<Livre, Lecteur>> entreelecteur = meslivresnondispos.entrySet();
		Iterator<Map.Entry<Livre, Lecteur>> itertoto2 = entreelecteur.iterator();
		while (itertoto2.hasNext()) {
			Map.Entry<Livre, Lecteur> liste2 = (Map.Entry) itertoto2.next();
			Livre liv = liste2.getKey();
			Lecteur lect = liste2.getValue();%>
                        
                 <div class="meslivreemprunt">
            <div class="imgidlivreemprunt">
      <img src="<%=liv.getImglivre()%>" alt="<%=liv.getTitre()%>"></img>
      <span class=idlivreemprunt>ID : <%=liv.getIdlivre()%></span>
    </div>
    <div class="taglivreemprunt">
        <span class=titrelivreemprunt><%=liv.getTitre()%></span>
        <span class=auteurlivreemprunt><%=liv.getAuteur()%></span>
        <span class=genrelivreemprunt><%=liv.getGenre()%></span>
      </div>
      <div class="etatlivreemprunt">
        <span class= "aspectlivre">Infos sur le livre / Aspect général :</span>
        <span class= "desclivre"><%=liv.getEtat()%></span>
      </div>
      <div class="boitelivreemprunt">
        <span class=dispoemprunt>EMPRUNTÉ</span>
        <p>par</p><span id=nomlecteuremprunt><%=lect.getNomLecteur()%></span>

      </div>
      <img src="boitenoire.png" alt="imglocboiteemprunt"></img>
</div>
        
        <%} %>
        
        <div class="ajouternouveaulivre"><a href="creerlivre.do">AJOUTER UN LIVRE</a></div>

        
      </div>
</div>



    </main>
<%@include file='footer.jsp' %>