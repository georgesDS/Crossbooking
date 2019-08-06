<%@page import="models.Boite"%>
<%@page import="java.util.ArrayList"%>
<%@include file='header.jsp' %>

<jsp:useBean id="trajet" scope="request" class="ArrayList<Boite>" />


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
            mapTypeId: google.maps.MapTypeId.SATELLITE
    });
    
    <% 
    for (Boite b : trajet){%>
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
    
    var flightPlanCoordinates = [
    <% 
    for (Boite b : trajet){%>
       {lat: <%=b.getGeoy()  %>, lng: <%=b.getGeox()  %>},

    <%}%>
    ];
    
    var flightPath = new google.maps.Polyline({
      path: flightPlanCoordinates,
      geodesic: true,
      strokeColor: '#0d47a1',
      strokeOpacity: 1.0,
      strokeWeight: 4
    });

    flightPath.setMap(map);
}




google.maps.event.addDomListener(window, 'load', initAutocomplete);


</script>
  

<main>
	
	<div id="main-container">
		<div id="fix"></div>
		<div id="main-content">
			<div id="map"></div>
		</div>	
</main>

<%@include file='footer.jsp' %>