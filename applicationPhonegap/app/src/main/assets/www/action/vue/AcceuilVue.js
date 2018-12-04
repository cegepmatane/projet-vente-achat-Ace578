var AcceuilVue = (function() {

    var pageAcceuil = document.getElementById("page-acceuil").innerHTML;

     return function() {



        this.afficher = function() {
			document.getElementsByTagName("body")[0].innerHTML = pageAcceuil;
            

        }


		
		
		
		
		

    };


})();
