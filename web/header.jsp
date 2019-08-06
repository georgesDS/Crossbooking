<%@page import="models.Lecteur"%>
<%@page import="java.util.Enumeration"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>CrossBooking</title>
  <link rel="stylesheet" href="header.css">
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="footer.css">
  <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  
</head>


<body>
<% 
    
session = request.getSession();
Lecteur l = null;
Enumeration<String> infos = session.getAttributeNames();
while(infos.hasMoreElements()){
    String content = (String) infos.nextElement();
    if (content.equals("id_connexion")){
        l = (Lecteur) session.getAttribute("id_connexion");
        break;
    }
}

%>

<header>
	<div id="header1">
		<div id="band-container">
			<div id="bienvenue">
				<span id="txt-bienvenue">Bienvenue sur CrossBooking </span><span id="txt-bienvenue2">la première plate-forme interactive d'échange de Livres.</span></div>
			<div id="social">
				<img src="social/facebook.png" alt="facebook" title="facebook">
				<img src="social/twitter.png" alt="twitter" title="twitter">
				<img src="social/mail.png" alt="mail" title="mail">
				<img src="social/googleplus.png" alt="googleplus" title="googleplus">
			</div>
		</div>
	</div>
		
		
	<div id="header2">	
		<div id="title-container">
                    <div id="logo"></div>
                        <a href="index.jsp" title="CrossBooking">
                            <span id="title">CrossBooking</span>
                        </a>
                            <% if (l != null){%><div id="user-log"><span id="lecteur-connecte"><%= l.getPrenomLecteur() %></span><a href="logout.do">logout</a></div><%}%>
                            <span id="slogan">Faire du Livre un objet en mouvement !</span>
                </div>
	</div>
	
</header>


<nav>
	<div id="nav-container">
		<img src="menu.png">
		<div class="bouton-nav"><a href="login.jsp">LOGIN</a></div>
		<div class="bouton-nav">ACTUALITÉS</div>
		<div class="bouton-nav"><a href="listelivres.do">Trouver un LIVRE</a></div>
                <div class="bouton-nav"><a href="carteboites.do">Les BOÎTES à LIVRES</a></div>
		<div class="bouton-nav">MODE D'EMPLOI</div>
	</div>
</nav>


