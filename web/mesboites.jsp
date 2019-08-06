
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="models.Boite"%>
<%@page import="java.util.HashMap"%>
<%@include file='header.jsp' %>

<jsp:useBean id="mesboites" scope="request" class="HashMap<Boite,Float>"/>
<jsp:useBean id="mescptboites" scope="request" class="HashMap<Boite,Integer>"/>


<main>
	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">
		
			<p>Vous pouvez retrouver ci-dessous vos boîtes à livres.</p>
			<p>Vous avez mis en place <span id="my-box-nb"><%= mesboites.size() %></span> BOITE(S) A LIVRES au sein du réseau CrossBooking.</p>
			
                        
                        <%
                        Iterator<Entry<Boite, Float>> entries = mesboites.entrySet().iterator();
                        while (entries.hasNext()) {
                            Entry<Boite, Float> thisEntry = entries.next();
                            Boite b = thisEntry.getKey();
                            Float moy = thisEntry.getValue();
                        
                        %>
                        
                        
                        
                        
			<div class="box-container">
				<div class="box-top">
					<img src="toit_boite_delete.png" usemap="#deleteboxmap"/>
					<map name="deleteboxmap">
						<area shape="rect" coords="180,25,210,55" href="" alt="Supprimer la boîte">
					</map>
				</div>
				<div class="box-content">
					<div class="box-top-content">
						<img src="<%=b.getImgboite() %>"/>
						<div class="box-content-infos">
							<p class="box-infos-number">Boîte n° <%=b.getIdboite()%> </p>
							<p class="box-infos-date">Créée le <%=b.getDatecreation()%></p>
							<p class="box-infos-city">Ville : <span class="box-infos-location"><%= b.getVille() %></span></p>
							<p class="box-infos-adress">Adresse : <span class="box-infos-location"><%= b.getAdresse()%></span></p>
							<p class="box-infos-time">Horaires d'accès : <span class="box-infos-nb"><%= b.getPlagehoraire()%></span></p>
							<p class="box-infos-content">Contenance estimée : <span class="box-infos-nb"><%= b.getContenancemaxi()%></span></p>
							<div class="box-infos-stars">
                                                                <% if (moy == 0) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                                                <% } %>
                                                                <% if (moy > 0 && moy<= 10) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png">
                                                                <% } %>
                                                                <% if (moy> 10 && moy<= 15 ) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png">
                                                                <% } %>
                                                                <% if (moy> 15 ) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png">
                                                                <% } %>
							</div>
						</div>
					</div>
                                                        
                                        <%
                                                Iterator<Entry<Boite, Integer>> entries2 = mescptboites.entrySet().iterator();
                                                while (entries2.hasNext()) {
                                                        Entry<Boite, Integer> thisEntry2 = entries2.next();
                                                        Boite b2 = (Boite) thisEntry2.getKey();
                                                        int cpt = (Integer) thisEntry2.getValue();

                                                        if (b2.getIdboite() == b.getIdboite()) {

                                            %>
                                                        
                                                        
                                                        
					<div class="box-bottom-content">
						<p><strong>Contenu actuel : </strong><em><%=cpt %> livres</em></p>
					</div>
				</div>
				<div class="box-modif">
					<a href=""><img src="modif_boite.png"/></a>
				</div>
			</div>
<% }}} %>
			<div class="box-container">
				<div class="box-top">
					<img src="toit_boite.png"/>
				</div>
				<div class="box-content">
					<div id="box-add">
						<a href="creerboite.jsp">AJOUTER UNE BOITE</a>
					</div>
				</div>
				<div class="box-modif">
				</div>
			</div>
			
			
		</div>
	</div>
	
</main>

<%@include file='footer.jsp' %>