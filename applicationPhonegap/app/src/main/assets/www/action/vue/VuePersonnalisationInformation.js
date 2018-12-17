var VuePersonnalisationInformation = (function() {

    var pagePersonnalisationInformation = document.getElementById("page-personnalisation-information").innerHTML;

     return function() {
     	var adresseLivraison;
     	var pays;
     	var adresseCourriel;
     	var nom;


        this.afficher = function() {
			document.getElementsByTagName("body")[0].innerHTML = pagePersonnalisationInformation;
		    /*nom = document.getElementsByClassName("nom")[0].value;

		    if(!(nom == "")){alert("bon");}
            document.getElementsByClassName("nom")[0].value = nom;
            document.getElementsByClassName("adresseLivraison")[0].value = adresseLivraison;
            document.getElementsByClassName("pays")[0].value = pays;
            document.getElementsByClassName("adresseCourriel")[0].value = adresseCourriel;*/
            



        }

    };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();