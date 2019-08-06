
<%@page import="models.Lecteur"%>
<%@include file='header.jsp' %>

<jsp:useBean id="lecteurcree" scope="request" class="Lecteur" />

<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">

			<div id="box-form-content">

								
				
                                <div id="bienvenue2">
                                    <h1>Bienvenue à toi,  <%= lecteurcree.getPrenomLecteur() %> ! </h1>
                                    <p> Ton Nom d'utilisateur est :<span id="identifiants">   <%= lecteurcree.getMail() %>  </span></p>
                                    <p> Ton Mot de Passe est :<span id="identifiants">    <%= lecteurcree.getNomLecteur()+lecteurcree.getPrenomLecteur() %>  </span></p>
                                    <a href="login.jsp" id="identif_connexion">Me connecter</a>
                                
                                </div>



				
			</div>
		</div>
	</div>




</main>

<%@include file='footer.jsp' %>