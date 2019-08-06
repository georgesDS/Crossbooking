<%@page import="java.util.Set"%>
<%@page import="models.Livre"%>
<%@page import="java.util.HashMap"%>
<%@include file="header.jsp"%>
<jsp:useBean id="livre_boite" scope="request" class="HashMap<Livre, Integer>"/>
<jsp:useBean id="livre_note" scope="request" class="Float"/>
<jsp:useBean id="livre_proprio" scope="request" class="String"/>



<%

		
    Set<Livre> livre = livre_boite.keySet();

    for (Livre livr : livre) {

            int idboite = livre_boite.get(livr);

%>

<main>
	<div id="main-container">
	<div id="fix"></div>
	<div id="main-content">
		<div id="contour-livre">
			<div id="page-gauche">
				<div id="sup-gauche">
					<div id="img-livre">
						<img src="<%= livr.getImglivre() %>" />
					</div>
					<div id="droite-imglivre">
						<div id="id-livre">
							<span>ID : </span> <span> <%= livr.getIdlivre()%> </span> <!-- IDLivre -->
						</div>
						<div id="statut-livre">
							<span><%= livr.getStatut() %></span>   <!--Statut-->
						</div>
						<div id="num-BAL">
                                                    <% if(idboite != -1){%>
                                                    <span>Boite n° <%= idboite %> </span>
                                                    <% } %>
							 <!--Numboite-->
						</div>
						<div id="genre-livre">
							<p>Genre :</p>
							<p><%= livr.getGenre() %></p>
						</div>
					</div>
				</div>
				<div id="milieu-gauche">
                                    
                                  <% if(l != null)
                                            {%>
                                            <a href="emprunterlivre.do?idlecteur=<%= l.getIdLecteur() %>&idlivre=<%= livr.getIdlivre()%>&idboite=<%= idboite %>"><div>EMPRUNTER</div></a>
                                  <%}else {%>
                                            <div>EMPRUNTER</div>
                                  <%}%>
                                    
				</div>
				<div id="inf-gauche">
                                         <% if(l != null)
                                            {%>
					<a href="trajet.do?idlivre=<%= livr.getIdlivre() %>">
                                            <%}else {%>
                                        <a href="">
                                            <%}%>
						<img src="earth.png"/>
						<div id="droite-img-planete">
							<p>Voir le</p>
							<p>TRAJET</p>
							<p>du Livre</p>
						</div>
					</a>
				</div>
			</div>
			<div id="page-droite">
				<div>
                                       <% if(livre_note==0)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                      <%}%>
                                      <% if(livre_note > 0 && livre_note<=10)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                      <%}%>
                                      <% if(livre_note>10 && livre_note<=15)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                      <%}%>
                                      <% if(livre_note>15)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                       <%}%>
					<span><%= livr.getTitre() %></span> <!-- Titre -->
				</div>
				<div>
					<span>Auteur : </span> <span><%= livr.getAuteur() %></span> <!-- Auteur -->
				</div>
				<div>
					<span>Appartient à </span><a href=""> <%= livre_proprio %></a> <!-- Proprietaire du livre -->
				</div>
				<div>
					<span>Infos sur le livre / Aspect général :</span>
					<p><%= livr.getEtat() %></p> <!-- etat du livre -->
				</div>
				<div>
					<button>Poster un avis</button>
					<button>Voir les avis</button>
				</div>
			</div>
</div>
</div>
</div>
</main>
                                        
<% } %>
<%@include file="footer.jsp"%>