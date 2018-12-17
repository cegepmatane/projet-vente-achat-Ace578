var VuePersonnalisationPayement = (function() {

    var pagePersonnalisationPayement  = document.getElementById("page-personnalisation-payement").innerHTML;

     return function() {

        this.afficher = function() {
			document.getElementsByTagName("body")[0].innerHTML = pagePersonnalisationPayement;
		    
        }

    };

        /*window.onload = function(){ 
        	

		};*/

})();