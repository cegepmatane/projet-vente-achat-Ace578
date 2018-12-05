var AcceuilVue = (function() {

    var pageAcceuil = document.getElementById("page-acceuil").innerHTML;

     return function() {



        this.afficher = function() {
			document.getElementsByTagName("body")[0].innerHTML = pageAcceuil;

        }

        window.onload = function(){ 
        	document.getElementById("buttonHomme").onclick = function() {myFunction()};;
			function myFunction() {
			    document.getElementById("buttonHomme").style.background = "blue";
			    document.getElementById("buttonFemme").style.background = "white";
			}
			document.getElementById("buttonFemme").onclick = function() {myFunction()};;
			function myFunction() {
			    document.getElementById("buttonHomme").style.background = "white";
			    document.getElementById("buttonFemme").style.background = "red";
			}

		};
        

    };


})();
