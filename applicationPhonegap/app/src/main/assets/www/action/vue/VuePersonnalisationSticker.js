var VuePersonnalisationSticker = (function() {

    var pagePersonnalisationSticker = document.getElementById("page-personnalisation-sticker").innerHTML;

     return function() {



        this.afficher = function() {
		  document.getElementsByTagName("body")[0].innerHTML = pagePersonnalisationSticker;
          

            



        }

        };

        /*window.onload = function(){ 
        	

		};*/
        

    


})();