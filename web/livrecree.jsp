<%-- 
    Document   : livrecree
    Created on : 28 avr. 2016, 16:41:53
    Author     : j.gibily
--%>


<%@page import="models.Livre"%>
<%@include file='header.jsp' %>


<jsp:useBean id="creationlivre2" scope="request" class="Livre" />

<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">

			<div id="box-form-content">

								
				
                                <div id="bienvenue3">
                                    <h1>Bravo à vous </h1>
                                    <p> Votre livre :  <span id="retourtitreajoutlivre">   <%= creationlivre2.getTitre() %>  </span></p>
                                    <p> a bien été ajouté !! </p>
                                    <p> <img src="<%= creationlivre2.getImglivre()%>" id="creerlivreimagertour"></p>
                                </div>

                                    


				
			</div>
		</div>
	</div>




</main>



<%@include file='footer.jsp' %>