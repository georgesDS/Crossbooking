<%@include file='header.jsp' %>

<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">
                    <p id="index-pindex">Sur CrossBooking, vous pouvez trouver près de chez vous des <span class= "spanindexindex">livres</span> mis à dispostion
                      par des membres du site.
                      Ces livres, vous les trouverez dans des <span class= "spanindexindex">Boîtes à livres</span>. Ces boîtes sont au coeur de la plate-forme d'échange de Crossbooking.
                      Inscrivez-vous et vous pourrez emprunter des livres, mettre à disposition certains de vos ouvrages, poster des actualités et même créer vos propres boîtes à livres.
                      Laissez donc vos livres voyager! Amusez-vous bien!
                    </p>
                    <div id="index-ensemble">
                        <div id="index-titre">
                            <div id="index-actu">Voir les ACTUALITES</div>
                            <div id="index-livre"><a href="listelivres.do">Chercher un LIVRE</a></div>
                            <div id="index-bal"><a href="carteboites.do">La carte des</a></div>
                            <div id="index-bal1"><a href="carteboites.do">BOÎTES A LIVRES</a></div>
                        </div>
                            <div id="index-img">
                                  <img src="planetecb.png">
                            </div>
                        <% if(l != null)
                            {%>
                          <div id="myaccount-container">
                              <div>MON COMPTE :</div>
                                  <div class="myaccount"><a href="meslivres.do">MES LIVRES</div>
                                  <div class="myaccount"><a href="mesemprunts.do">MES EMPRUNTS</div>
                                  <div class="myaccount"><a href="mesboites.do">MES BOÎTES A LIVRES</a></div>
                                  <div class="myaccount">MES ACTUALITÉS</div>
                          </div>
                          <%}%>
                    </div>
		</div>
	</div>




</main>



<%@include file='footer.jsp' %>