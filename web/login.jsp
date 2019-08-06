<%@include file='header.jsp' %>

<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">


			<div id="box-form-content">
				<div id="logingnrl">
                                    <h1 id="login-titre">Identifiez-vous</h1>
                                </div>
                            
				<form action="login.do" method="POST">
                                    <div id="login-nom">
                                            <p id="login-user">Nom d'utilisateur :</p>
                                            <input id="login-ajoutuser" type="text" name="log">
                                    </div>
                                    <div id="login-mdp">
                                            <p id="login-mdp2">Mot de passe :</p>
                                            <input id="login-ajoutmdp" type="password" name="pass">
                                    </div>

                                    <input type="submit" id="login-connecter" value="Me connecter">
                                </form>
				
                                <div id="pas-de-login">
                                      <a href="creerlecteur.jsp">Je n'ai pas de compte Crossbooking</a>
				</div>
			</div>
		</div>
	</div>
</main>

<%@include file='footer.jsp' %>