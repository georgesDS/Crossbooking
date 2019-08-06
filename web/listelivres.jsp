
<%@page import="models.Livre"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>

<%@include file='header.jsp' %>

<jsp:useBean id="listelivres" scope="request" class="HashMap<Livre, Float>" />
  
        
<main>
	
	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">
		
		
			<div id="list-search">
				<span class="list-titles">Rechercher</span>
				<form>
					<input type="text" name="recherche">
					<input type="submit" value="Ok">
				</form>
			</div>
			
			<div id="list-container">
				<div id="list-filter-container">
					<span class="list-titles">Filtrer</span>
					<span class="list-subtitles">Genre</span>
					<div id="list-filter-genre">
						<form>
							<label><input type="checkbox" value="BD">&nbsp;&nbsp;BD</label><br>
							<label><input type="checkbox" value="Roman">&nbsp;&nbsp;Roman</label><br>
							<label><input type="checkbox" value="Récit">&nbsp;&nbsp;Récit</label><br>
							<label><input type="checkbox" value="Policier">&nbsp;&nbsp;Policier</label><br>
							<label><input type="checkbox" value="Jeunesse">&nbsp;&nbsp;Jeunesse</label><br>
							<label><input type="checkbox" value="Divers">&nbsp;&nbsp;Divers</label><br>
						</form>
					</div>
					<span class="list-subtitles">Note</span>
					<div id="list-filter-note">
						<form>
							<label><input type="checkbox" value="3">&nbsp;&nbsp;<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"></label><br>
							<label><input type="checkbox" value="2">&nbsp;&nbsp;<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"></label><br>
							<label><input type="checkbox" value="1">&nbsp;&nbsp;<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"></label><br>
						</form>
					</div>
				</div>
				<div id="list-book-container">
                                    
                                    
                                    
                                 <% Set<Map.Entry<Livre, Float>> ent = listelivres.entrySet();
                                    Iterator<Map.Entry<Livre, Float>> iter2 = ent.iterator();
		
                                    while (iter2.hasNext()) {
                                        
                                        Map.Entry<Livre, Float> liste2 = (Map.Entry) iter2.next();
                                        Livre liv = liste2.getKey();
                                        Float etoiles = liste2.getValue(); %>
                                    
                            
					<div class="list-book-book">
                                            <img src="<%= liv.getImglivre()%>" />
						<div class="book-infos">
							<a href="consulterlivre.do?idlivre=<%= liv.getIdlivre() %>"><span> <%= liv.getTitre() %> </span></a>
							<span> <%= liv.getAuteur() %> </span>
							<div>
								<span> <%= liv.getGenre() %> </span>
                                                        
                                                        <div>       
                                                                <% if (etoiles == 0 ) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                                                <% } %>
                                                                <% if (etoiles>0 && etoiles<= 10) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                                                <% } %>
                                                                <% if (etoiles> 10 && etoiles<= 15 ) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                                                <% } %>
                                                                <% if (etoiles> 15 ) {%>
								<img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"><img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png">
                                                                <% } %>
							</div>
                                                        </div>
						</div>                                           
					</div>
                                                                
                          
                                <% }%>
                            
                            
					
					<div id="list-book-nav">
						<span>Page : </span>
						<span style='color:red'><a href="">1</a></span>
						<span><a href="">2</a></span>
						<span><a href="">3</a></span>
						<span>...</span>
						<span><a href="">20</a></span>
						<span>|</span>
						<span><a href="">Suivante ></a></span>
					</div>	
				</div>

				<div id="list-right-container">
					<div id="list-sort-container">
						<span class="list-titles">Trier</span>
						<form>
							<select name="sort">
								<option>Plus récents en premier
								<option>Plus anciens en premier
							</select>
						</form>
						<span class="list-titles">Afficher</span>
						<form>
							<select name="affich">
								<option>5 Livres
								<option>10 Livres
								<option>20 Livres
								<option>50 Livres
								<option>100 Livres
							</select>
						</form>
					</div>
					<div id="list-ad-container">
						<span>Publicité</span>
						<img src="min_ad.jpg">
					</div>
				</div>
				
			</div>
		

		</div>
	</div>

		
</main>
        
<%@include file='footer.jsp' %>
