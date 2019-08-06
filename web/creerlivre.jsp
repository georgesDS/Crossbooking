<%-- 
    Document   : creerlivre
    Created on : 28 avr. 2016, 15:46:16
    Author     : j.gibily
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Boite"%>
<%@page import="models.Livre"%>
<%@include file='header.jsp' %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script type="text/javascript">
function readURLv2(input) {
        var uri = input;
        $('#preview').attr('src', uri);    
}
</script>
<jsp:useBean id= "creationlivre" scope="request" class= "ArrayList<Boite>"/>



<main>

      <div id="main-container">
        <div id="fix"></div>
        <div id="main-content">

          <!-- CODEZ ICI SI pas formulaire -->
<form action="livrecree.do" method="POST">
          <div id="box-form-content_creerlivre">
              
            <div id="principal">

              <h1>Ajouter un LIVRE</h1>
              <p id=description>
                Une fois le formulaire validé, votre livre sera disponible à l'emprunt.
                Assurez-vous de l'avoir déposé au préalable dans une de nos boîtes à livres.
              </p>

   
              <div id="gauche">
                <div class="blocsaisie" id="bloctitre">
  
                  <h2 id="titre">Titre  :</h2>
                  <span class="asterisque">*</span>
                  <input id="ajouttitre" type="text" name="titre" />
                </div>
                <div class="blocsaisie" id="blocauteur" >
                  <h2 id="auteur">Auteur  :</h2>
                  <span class="asterisque">*</span>
                  <input id="ajoutauteur" type="text" name="auteur"/>
                </div>
                  <div class="blocsaisie" id="blocauteur" >
                  <h2 id="auteur">Etat  :</h2>
                  
                  <input id="ajoutauteur" type="text" name="etat"/>
                </div>
                  
                <div class="blocsaisie" id="blocgenre">
                  <h2 id="genre">Genre  :</h2>
                  <span class="asterisque">*</span>
                  <label for="genre"></label>
                  <select name="genre" id="ajoutgenre" type="text">
                    <option value="Policier">Policier</option>
                    <option value="Aventures">Aventures</option>
                    <option value="Roman">Roman</option>
                    <option value="Biographie">Biographie</option>
                    <option value="Histoire">Histoire</option>
                    <option value="Bande-Dessinée">Bande-Dessinée</option>
                    <option value="Jeunesse">Jeunesse</option>
                  </select>
                </div>
                <div class="blocsaisie" id="blocboite">
                  <h2 id="boite">Boîte de dépôt  :</h2>
                  <span class="asterisque">*</span>
                  <label for="boite"></label>
                  <select name="idboite" id="ajoutboite">
                    <%for(Boite b: creationlivre){%>
                    <option value="<%=b.getIdboite()%>">N°<%=b.getIdboite()%> - <%=b.getAdresse()%> - <%=b.getVille()%> </option>;
                      <%}%>
                      
                    
                  </select>
                  </div>
                  <div class="blocsaisie">
                    <span id=champ>* Champ obligatoire</span>
                  </div>

              </div>
              <div id="bas">
                  <input type="hidden" name="statut" value="DISPONIBLE">
                  <input type="submit" id="ajouter" value="AJOUTER">
              </div>
              </div>
              <div id="droite">
                <!--<input type="button" value="Insérer une IMAGE">-->
                <input type='text' id="url" style="width:200px" name="imglivre" placeholder="http://..."/>
                <!--<input type="button" value="Insérer une IMAGE">-->
                <img id="preview" style="max-width:150px" src="img-empt.jpg"/>
              </div>
          </form>
        <script type="text/javascript">
        $("#url").on('input',function () {
        readURLv2(this.value);
         });
        </script>
          </div>
        </div>
      </div>
</main>


<%@include file='footer.jsp' %>