<%@page import="java.util.LinkedList"%>
<%@page import="models.Livre"%>
<%@page import="forms.Form_Boite_Consulte"%>
<%@page import="models.Boite"%>
<%@include file="header.jsp"%>
<jsp:useBean id="essaicatie" scope="request" class="Form_Boite_Consulte"/>
<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">

			<!-- CODEZ ICI SI pas formulaire -->
        <div id="maison">
          <div id="toitboite">
            <img class="toit" src="TOITBOITE1.png" />
          </div>
          <div id="corpsboite">
             
            <div id="part1">
                  <div id="imageboite">
                          <img src="<%=essaicatie.getImgBoite()%>" />
                  </div>
                   
                  <div id="infoboite">
                        <div id="infoboite1">
                            
                             <div id="titreetnoboite">
                                  <div id="titreb">
                                      <p>Boite n° <span class="numboite"> <%=essaicatie.getIdBoite()%> </span></p>
                                  </div>
                                  <div id="etoiles">
                                       <% if(essaicatie.getNoteBoite()==0)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_empt.png">
                                      <%}%>
                                      <% if(essaicatie.getNoteBoite() > 0 && essaicatie.getNoteBoite()<=10)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                      <%}%>
                                      <% if(essaicatie.getNoteBoite()>10 && essaicatie.getNoteBoite()<=15)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                      <%}%>
                                      <% if(essaicatie.getNoteBoite()>15)
                                            {%>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                            <img src="http://le-fil-cine.com/wp-content/uploads/2013/02/star_full.png"/>
                                       <%}%>
                                                
                                                
                                  </div>
                             </div>
                             <!--modif catie le 03 MAI -->
                             <div id="creationboite">
                                  <p>Créée le <span class="datecreat"> <%=essaicatie.getDatecreation()%>  par  </span><span class="createur"> <%=essaicatie.getLecteurprop()%> </span></p>
                             </div>
                             <div id="adresseboite">
                                  <p>Ville : <span class="vil"> <%=essaicatie.getVille()%> </span></p>
                                  <p>Adresse : <span class="adr"> <%=essaicatie.getAdresse()%> </span></p>
                             </div>
                             <div id="complboite">
                                  <p>Horaires d'accès : <span class="hor"> <%=essaicatie.getPlagehoraire()%> </span></p>
                                  <p>Contenance estimée : <span class="cont"> <%=essaicatie.getContenancemaxi()%> </span></p>
                              </div>

                        <!-- fin infoboite1-->
                        </div>

                        <div id="infoboite2">
                              <div id="locboite">
                                 <!--modif par catie le 03 mai-->
                                  <% if(l != null)
                                            {%>
                                       <div id="geoloc">
                                      <p> X :<span class="geolocx"> <%=essaicatie.getGeox()%> </span>  </p>
                                      <p> Y :<span class="geolocy"> <%=essaicatie.getGeoy()%> </span>  </p>
                                  </div>
                                  <%}else {%>
                                      <div id="geoloc">
                                      <p> X :<span class="geolocx"> masqué</span>  </p>
                                      <p> Y :<span class="geolocy"> masqué</span>  </p>
                                  </div>      
                                  <%}%>
 
                                  <div id="dept">
                                    <p> <span class="numdept"> <%=essaicatie.getDepartement()%> </span></p>
                                    <!--
                                    <p> <span class="nomdept"> >Haute Garonne</span></p> 
                                    -->
                                  </div>
                              <!--fin loc-->
                              </div>

                              <div id="action">
                                     <div id="posteravis">
                                        <button>Poster un avis</button>
                                     </div>
                                     <div id="consulteravis">
                                        <button>Voir les avis</button>
                                     </div>

                              <!-- fin action-->
                              </div>

                        <!-- fin info2boite-->
                        </div>



                  <!-- fin infoboite-->
                  </div>
            <!--finpart1-->
            </div>

            <div id="part2">
                  <div id="contenuboite">
                      <p> Contenu actuel : <span class="contboite"> <span class="createur"> <%=essaicatie.getNblivres() %> livre(s)</span></span> </p>
                  </div>
                  <div id="listelivres">
                      
                      <table>
                         <% for(Livre livaff : essaicatie.getListgt())
                         {
                         
                         %>
                         <tr>
                          <td> <span class="genre"><%=livaff.getGenre()%> :</span></td>
                          <td><a href="consulterlivre.do?idlivre=<%= livaff.getIdlivre() %>"><span class="titrelivre"><%=livaff.getTitre()%></span></a></td>
                          
                         </tr>
                         
                             
                         <%
                         }
                         %>
                         
                
                      </table>
                    </div>
            </div>
          <!--fincorpsboite-->
          </div>
        <!-- finmaison-->
        </div>



			<!--<div id="box-form-content">


				CODEZ ICI SI fond blanc, bloc bleu formulaire et planete en fond.


        /div
			-->

    <!--fin main-content-->
    </div>

  <!--fin main container-->
  </div>




</main>
<%@include file="footer.jsp"%>

