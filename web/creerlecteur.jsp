<%@include file='header.jsp' %>

<main>

	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">

			<div id="box-form-content">

				<div id="bandeauCMC">
				<div>Créer mon compte</div>
				<img src="Image.png">
				</div>

				<div id="CMC">
					<ul id="LeftChampsCMC">
						<li>Nom:</li>
						<li>Prenom:</li>
						<li>Mail<span>*</span> :</li>
						<li><span>*Champ obligatoire</span></li>
					</ul>
                                        <FORM action="creerlecteur.do" method="POST" name="newlecteur" onsubmit="return VerifMail()">
                                            <script type="text/javascript">
                            
                                                    function VerifMail()
                                                        {
                                                            a = document.newlecteur.monmail.value;
                                                            valide1 = false;

                                                            for(var j=1;j<(a.length);j++){
                                                                    if(a.charAt(j)==='@'){
                                                                            if(j<(a.length-4)){
                                                                                    for(var k=j;k<(a.length-2);k++){
                                                                                            if(a.charAt(k)==='.') valide1=true;
                                                                                    }
                                                                            }
                                                                    }
                                                            }
                                                            if(!valide1){ alert("Veuillez saisir une adresse email valide.");
                                                            return valide1;
                                                            }
                                                        }

                                                    function ShowAlerte() {
                                                        alert("Vous devez accepter la charte CrossBooking avant de valider");
                                                    }

                                                    function ValideForm() {
                                                        if (document.getElementById('btnaccepte').checked === false) {
                                                            document.getElementById('btmvalide').hidden = true;
                                                            document.getElementById('btnfactice').hidden = false;
                                                        }
                                                        if (document.getElementById('btnaccepte').checked === true) {
                                                            document.getElementById('btmvalide').hidden = false;
                                                            document.getElementById('btnfactice').hidden = true;                                    
                                                        }

                                                    }
                                                </script> 
                                                <ul id="MiddleChampsCMC">
                                                    <li><input id="Nom" name="nomLecteur" type="text" value=""/></li>
                                                    <li><input id="Prenom" name="prenomLecteur" type="text" value=""/></li>
                                                    <li><input id="e-mail" name="monmail" type="text" value=""/></li>

                                                    <li><input type="submit" id="btmvalide" value="Valider l'inscription" hidden></li>
                                                    <li><input type="button" id="btnfactice" value="Valider l'inscription" onclick="ShowAlerte()"></li>
                                                </ul>
					</FORM>
					<div id="RightChampsCMC">
						<h3>Charte de CrossBooking</h3>
						<p>Je m'engage en tant que membre de CrossBooking quo cognito Constantius ultra mortalem modum exarsit
              ac nequo casu idem Gallus de futuris incertus agitare quaedam conducentia saluti suae per itinera conaretur,
              remoti sunt omnes de industria milites agentes in civitatibus perviis
            ac nequo casu idem Gallus de futuris incertus agitare quaedam conducentia saluti suae per itinera conaretur,
            remoti sunt omnes de industria milites agentes ac nequo casu idem Gallus de futuris incertus agitare quaedam
            conducentia saluti suae per r</p>
						<p>Signer la charte<input id="btnaccepte" type="checkbox" name="regagree" onClick="ValideForm()"/></p>

					</div>
				</div>
			</div>
		</div>
	</div>




</main>

<%@include file='footer.jsp' %>