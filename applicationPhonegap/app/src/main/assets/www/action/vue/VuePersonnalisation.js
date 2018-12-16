var VuePersonnalisation = (function() {

    var pagePersonnalisation = document.getElementById("page-personnalisation").innerHTML;

     return function() {



        this.afficher = function() {
		  document.getElementsByTagName("body")[0].innerHTML = pagePersonnalisation;
          

            



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();