<%@page import="models.Boite"%>
<%@page import="java.util.ArrayList"%>
<%@include file='header.jsp' %>

<jsp:useBean id="listeboites" scope="request" class="ArrayList<Boite>" />


<script src="http://maps.googleapis.com/maps/api/js?libraries=places"></script>
<script>
    
var map;

function affichePosition(position) {

    // On instancie un nouvel objet LatLng pour Google Maps
    latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    map.panTo(latlng);
}

function initAutocomplete() {

    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(affichePosition);
    }
    
    
    map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 43.566441, lng: 1.466448},
            zoom: 15,

            <% if (l == null){%> scrollwheel:  false,  zoomControl: false,   <%}%>

            mapTypeId: google.maps.MapTypeId.SATELLITE
    });

    
    <% 
    for (Boite b : listeboites){%>
    var marker<%= b.getIdboite() %>=new google.maps.Marker({
            position:new google.maps.LatLng(<%= b.getGeoy() %>,<%= b.getGeox() %>),
            icon: 'http://www.icone-png.com/png/40/39759.png',
            url: 'consulterboite.do?idboite=<%= b.getIdboite() %>'
      });
    marker<%= b.getIdboite() %>.setMap(map);

    google.maps.event.addListener(marker<%= b.getIdboite() %>, 'click', function() {
            window.location.href = this.url;
    });

    <%}%>

    // Create the search box and link it to the UI element.
    var input = document.getElementById('search');
    var searchBox = new google.maps.places.SearchBox(input);


    // Listen for the event fired when the user selects a prediction.
    searchBox.addListener('places_changed', function() {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
          return;
        }

        var bounds = new google.maps.LatLngBounds();
        places.forEach(function(place) {

          if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
          } else {
                bounds.extend(place.geometry.location);
          }
        });
        map.fitBounds(bounds);
        map.setZoom(15);
    });
    
}



google.maps.event.addDomListener(window, 'load', initAutocomplete);


</script>
  

<main>
	
	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">
		

			<div id="map-top">
				<div id="map-top-left">
                                    
					<p>Pour plus de rapidité, autorisez votre navigateur à utiliser vos données de localisation.</p>
					<div id="map-search">
						<span>Zoomer sur :</span>
						<input type="text" name="search" id="search" placeholder="Rechercher...">
					</div>
				</div>
				<div id="map-top-right">
                                    
                                    <% if (l == null){%>   
					<p>Vous n'êtes pas connecté.</p>
					<p><a href="login.jsp">Authentifiez-vous</a> pour débloquer le Zoom de la carte</p>
                                    <%}%>
                                </div>
			</div>
			<div id="map"></div>
		
		
		</div>



		
</main>

<%@include file='footer.jsp' %>